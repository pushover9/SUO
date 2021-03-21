package kr.suo.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.suo.service.BoardService;
import kr.suo.service.ReplyService;
import kr.suo.vo.BoardVO;


@Controller
@RequestMapping("/main/*")
public class BoardController {
	
	@Autowired//자동 의존성 주입.
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("board_write")
	public ModelAndView board_write(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String id=(String)session.getAttribute("id");
		String naverId=(String)session.getAttribute("sessionid");
		if ((id == null)&&(naverId == null)) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요');");
			out.println("location='login';");
			out.println("</script>");
		} else if((id != null) && (naverId == null)){
			/* UserVO u = this.accountService.getUser(id); */
			ModelAndView mv = new ModelAndView();
			session.setAttribute("id", id);
			
			mv.setViewName("board/board_write");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			session.setAttribute("id", naverId);
			
			mv.setViewName("board/board_write");
			return mv;
		}
		return null;
	}		
	
	@PostMapping("board_write_ok")
	public ModelAndView board_write_ok(BoardVO b,RedirectAttributes rttr,HttpServletRequest request,
			MultipartHttpServletRequest mhsq) throws IllegalStateException,IOException {
			
			boardService.insertBoard(b);
			String realFolder = request.getRealPath("/resources/upload/");
			System.out.println(realFolder);
			File dir = new File(realFolder);
			if(!dir.isDirectory()) {
				dir.mkdir();
			}
			
			List<MultipartFile> mf = mhsq.getFiles("uploadFile");
			if(mf.size()==1 && mf.get(0).getOriginalFilename().equals("")){
				
			}else {
				for(int i = 0; i< mf.size(); i++) {
					String genId = UUID.randomUUID().toString();
					String originalfileName = mf.get(i).getOriginalFilename();
				
					int index = originalfileName.lastIndexOf(".");
					String fileExtendsion = originalfileName.substring(index + 1);
					
					String saveFileName = genId + "." + fileExtendsion;
					
					String savePath = realFolder+"/board/"+ saveFileName;
					
					long fileSize = mf.get(i).getSize();
					
					mf.get(i).transferTo(new File(savePath));
	
					boardService.fileUpload(originalfileName,saveFileName,fileSize);
				}
			
			
		}
		rttr.addFlashAttribute("msg", "SUCCESS");
		return new ModelAndView("redirect:/main/board");
	}
	@RequestMapping("board")
	public ModelAndView board_list(@RequestParam(value="cate",defaultValue = "통합") String cate,
			HttpServletRequest request,BoardVO b,HttpServletResponse response) throws Exception {
		System.out.println(cate);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(request.getParameter("find_name"));
		System.out.println(request.getParameter("find_field"));

		ModelAndView m = new ModelAndView();
		int page=1;
		int limit=10;
		int totalCount;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		
		//검색전후
		String find_name=request.getParameter("find_name");//검색어
	    String find_field=request.getParameter("find_field");//검색필드
	    
	
	    b.setFind_field(find_field);
	    b.setFind_name("%"+find_name+"%");//%는 쿼리문에서 하나이상의 임의의 모르는 문자와 매핑 대응하는 와일드카드 문자
		
		
		
		List<BoardVO> blist;
		b.setStartrow((page-1)*10+1);//시작행 번호
		b.setEndrow(b.getStartrow()+limit-1);//끝행번호
		
		
		if(cate.equals("통합")) {
			totalCount = this.boardService.getTotalCount();
			blist=this.boardService.getBoardList(b);
	
			m.addObject("list", blist);//list
			m.addObject("totalCount", totalCount);//totalCount키이름에  총 레코드 개수 저장
		}else {
			b.setCategory(cate);
			totalCount = this.boardService.getCateCount(b); 		
			blist = this.boardService.getCateList(b);
			
			m.addObject("list", blist);//list
			m.addObject("totalCount", totalCount);//totalCount키이름에  총 레코드 개수 저장
			m.addObject("cate",cate);
		}
			
		System.out.println(totalCount);
		//페이징 연상
		int maxpage=(int)((double)totalCount/limit+0.95);//총페이지 수; 합해서 1이 되도력 소수점을 조정하기.
		int startpage=(((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage; //현재 페이지에 보여질 마지막 페이지;
		
		if(endpage> startpage+10-1) {
			endpage = startpage +10 -1;
		}
		m.addObject("find_field",find_field);
		m.addObject("find_name",find_name);
		m.addObject("startpage", startpage);
		m.addObject("endpage", endpage);
		m.addObject("maxpage", maxpage);
		m.addObject("page", page);//page키이름에 현재 쪽번호 저장함.
		m.setViewName("./board/board_list");//뷰페이지 경로 설정
		return m;
	}
	
		
	//게시물 내용보기
	@RequestMapping(value="/board_cont", method=RequestMethod.GET )
	public String board_cont(@RequestParam("bno") int bno, @RequestParam("page") int page, 
			BoardVO bv,Model m,HttpSession session) {
		
		BoardVO b = this.boardService.getCont(bno);
		
		System.out.println(b.getReply_cnt());
		
	    List<BoardVO> upload = boardService.getFileList(bno);

	    m.addAttribute("upload",upload);
		m.addAttribute("b",b);
		m.addAttribute("page", page);
		
		return "./board/board_cont2"; 
		
	}
	
	@RequestMapping(value="/board_edit", method=RequestMethod.GET)
	public ModelAndView board_edit(/*@RequestParam("bno")*/ int bno, int page) {
		BoardVO eb = this.boardService.getCont(bno);
		
		ModelAndView em = new ModelAndView("board/board_edit");
		em.addObject("b", eb);//b키이름에 eb객체 저장.
		em.addObject("page", page);
		return em;
	}
	
	@RequestMapping(value="/board_edit_ok",method= RequestMethod.POST)
	public String board_edit_ok(BoardVO eb, int page, RedirectAttributes rttr) {
		
		this.boardService.editBoard(eb);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/main/board?page="+page;
	}
	
	@RequestMapping(value="/board_del", method=RequestMethod.GET)
	public ModelAndView board_del(int bno, int page, RedirectAttributes rttr) {
		this.boardService.delBoard(bno);
		rttr.addFlashAttribute("msg","SUCCESS");
		ModelAndView dm = new ModelAndView("redirect:/main/board");
		dm.addObject("page", page);
		return dm;
	}
	
}

