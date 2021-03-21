package kr.suo.controller;


import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

import kr.suo.service.AccountService;
import kr.suo.vo.UserVO;
import net.naver.test.NaverLoginBO;
import pwdconv.PwdChange;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	//네이버
	@Autowired 
	private NaverLoginBO naverLoginBO;
	
	private String apiResult = null;
	//네이버
	
	@RequestMapping(value="/main/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session,HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id=(String)session.getAttribute("id");
		String naverId = (String) session.getAttribute("sessionid");
		
		if (id != null || naverId != null) {
			out.println("<script>");
			out.println("location='/index';");
			out.println("</script>");
		} else {
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			model.addAttribute("url", naverAuthUrl);
			return "/main/login";
		}
		
		return null;
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception {
	System.out.println("여기는 callback");
	OAuth2AccessToken oauthToken;
	
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String nickname = (String)response_obj.get("nickname");
		System.out.println(nickname);
		//4.파싱 닉네임 세션으로 저장
		session.setAttribute("sessionid",nickname); //세션 생성
		model.addAttribute("result", apiResult);
	
	return "/index/index";
	}
	
	@PostMapping("/overlab")
	public ModelAndView overlab(UserVO b,HttpServletRequest request, HttpServletResponse response, HttpSession session)
		throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		int result = -1;
		UserVO user_id = this.accountService.overlab(b.getUser_id());
		PrintWriter out = response.getWriter();
		if (user_id == null) {
			result = 1;
		}
		out.println(result);
		return null;
	}
	
	
	@PostMapping("/join_ok")
	public ModelAndView join_ok(UserVO b, HttpServletResponse response, HttpServletRequest request, HttpSession session)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		b.setUser_id(request.getParameter("user_id"));
		b.setUser_pw(PwdChange.getPassWordToXEMD5String(request.getParameter("user_pw")));
		b.setUser_name(request.getParameter("user_name"));
		b.setUser_email01(request.getParameter("user_email01"));
		b.setUser_email02(request.getParameter("user_email02"));
		b.setUser_phone01(request.getParameter("user_phone01"));
		b.setUser_phone02(request.getParameter("user_phone02"));
		b.setUser_phone03(request.getParameter("user_phone03"));
		this.accountService.userJoin(b);
		out.println("<script>");
		out.println("alert('회원가입이 완료되었습니다!');");
		out.println("location.href='/index';");
		out.println("</script>");
		
		
		return null;
	}

	@PostMapping("/login_ok")
	public ModelAndView login_ok(UserVO b, HttpServletResponse response, HttpSession session) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserVO user = this.accountService.userLogin(b.getUser_id());

		if (user == null) {
			out.println("<script>");
			out.println("alert('유저 계정이 없습니다');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else {
			if (!user.getUser_pw().equals(PwdChange.getPassWordToXEMD5String(b.getUser_pw()))) {
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				session.setAttribute("id", b.getUser_id());
				return new ModelAndView("redirect:/index");
			}
		}
		return null;
	}// login_ok
	
	@RequestMapping("/user_edit")
	public ModelAndView user_edit(HttpServletResponse response, HttpSession session, HttpServletRequest request)throws Exception {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String id=(String)session.getAttribute("id");
			
			if (id == null) {
				out.println("<script>");
				out.println("alert('다시 로그인하세요');");
				out.println("location='/main/login';");
				out.println("</script>");
			} else {
				UserVO u = this.accountService.getUser(id);
				
				ModelAndView mv = new ModelAndView();
				mv.addObject("u", u);
				mv.setViewName("/main/user_edit");
				return mv;
			}
		return null;
	}
	
	@RequestMapping("user_edit_ok")
	public String user_edit_ok(UserVO u,HttpServletResponse response,HttpSession session, HttpServletRequest request)throws Exception{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String id=(String)session.getAttribute("id");
			
			if (id == null) {
				out.println("<script>");
				out.println("alert('다시 로그인하세요');");
				out.println("location='/main/login';");
				out.println("</script>");
			} else {
				u.setUser_id(id);
				u.setUser_pw(PwdChange.getPassWordToXEMD5String(u.getUser_pw()));
				this.accountService.editUser(u);
				
				
				out.println("<script>");
				out.println("alert('정보 수정을 완료했습니다!');");
				out.println("location='index';");
				out.println("</script>");
				
				
				return null;
			}
			
		return null;
	}
	
	@RequestMapping("/find_id")
	public String find_id() {
		return "/main/find_id";
	}//아이디 찾기
	
	@RequestMapping("/find_id_ok")
	public ModelAndView find_id_ok(UserVO b, HttpServletResponse response ) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserVO user =this.accountService.findId(b);
		ModelAndView mv = new ModelAndView();
		if(user == null) {
			out.println("<script>");
			out.println("alert('유저 계정이 없습니다');");
			out.println("history.go(-1);");
			out.println("</script>");
			
		}else {
			String user_id = user.getUser_id();
			mv.setViewName("/main/find_id");
			mv.addObject("user_id", user_id);
			return mv;  
		}
		return null;
	}
	
	@RequestMapping("/find_pw")
	public String find_pw() {
		return "/main/find_pw";
	}//비밀번호 찾기
	
	@RequestMapping("/find_pw_ok")
	public String find_pw_ok(UserVO b, HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserVO ran_pw = this.accountService.findPw(b);
		ModelAndView mv = new ModelAndView();
		if(ran_pw == null) {
			out.println("<script>");
			out.println("alert('유저 계정이 없습니다');");
			out.println("history.go(-1);");
			out.println("</script>");
		}else {
			int pwLength = 8                                                                                      ;
			char[] passwordTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
					'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
					'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
					'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
					'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '1', '2', 
					'3', '4', '5', '6', '7', '8', '9', '0' };
		
			Random random = new Random(System.currentTimeMillis()); 
			int tablelength = passwordTable.length; 
			String up_pw = ""; 
			for(int i = 0; i < pwLength; i++) { 
				up_pw += passwordTable[random.nextInt(tablelength)]; 
			} 
			
			ran_pw.setUser_pw(PwdChange.getPassWordToXEMD5String(up_pw));
			this.accountService.updatePw(ran_pw);
			model.addAttribute("up_pw", up_pw);
			return "/main/find_pw";
		}
		return null;
	}
	
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletResponse response, HttpSession session) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		
		session.invalidate();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location='/index';");
		out.println("</script>");
		
		return null;
	}
	
	@PostMapping("/edit")
		public String edit(HttpServletResponse response,HttpSession session) throws Exception{
		return "/main/edit";
	}

}
