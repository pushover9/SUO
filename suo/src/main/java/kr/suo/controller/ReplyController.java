package kr.suo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.suo.service.ReplyService;
import kr.suo.vo.ReplyVO;


@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Autowired
	public ReplyService replyService;
	
	//댓글 등록
	@RequestMapping(value="",method=RequestMethod.POST) //post방식으로 접근하는 매핑주소를 처리하는 애노테이션
	public ResponseEntity<String> register(@RequestBody ReplyVO vo, HttpSession session){
		/* @RequestBody 애노테이션은 전송된 JSON 데이터를 객체로 변환한다. 데이터 전송방식은 json을 이용
		 * JSON으로 보내진 데이터를 ReplyVO 객체 타입으로 변환시켜 준다.
		 */
		ResponseEntity<String> entity=null;
		String id;
		if(session.getAttribute("id")!=null) {
			id = (String)session.getAttribute("id");
			vo.setReplyer(id);
		}else if(session.getAttribute("sessionid")!=null){
			id = (String)session.getAttribute("sessionid");
			vo.setReplyer(id);
		}
		
		
		try {
			this.replyService.addReply(vo);//댓글 등록
			entity= new ResponseEntity<String>("SUCCESS",HttpStatus.OK);//댓글 저장 성공시 정상코드 200반환, 동시에 SUCCESS문자열반환
		}catch(Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);//예외 에러 발생, 나쁜상태코드
			//문자열이 반환된다.
		}
		return entity;
	}//register()
	
	//게시물 번호에 해당하는 댓글 목록
	@RequestMapping(value="/all/{bno}",method=RequestMethod.GET)//GET으로 접근하는 매핑주소 처리
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno")int bno){
		//@PathVariable("bno")는 매핑주소의 bno즉 게시판 번호값을 추출한는 용도로 활용된다.
		ResponseEntity<List<ReplyVO>> entity=null;
		
		try {
			entity=new ResponseEntity<>(this.replyService.listReply(bno),HttpStatus.OK);//게시물번호에 해당하는 댓글 목록
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//list()
	
	//댓글 수정
	@RequestMapping(value="/{rno}",method= {RequestMethod.PUT, RequestMethod.PATCH})
	//PUT은 전체자료 수정,PATCH는 일부 자료 수정
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody() ReplyVO vo,HttpSession session){
		ResponseEntity<String> entity=null;
		try {
			String id;
			if(session.getAttribute("id")!=null) {
				id = (String)session.getAttribute("id");
				vo.setReplyer(id);
			}else if(session.getAttribute("sessionid")!=null){
				id = (String)session.getAttribute("sessionid");
				vo.setReplyer(id);
			}
			vo.setRno(rno);
			this.replyService.updateReply(vo);
			entity= new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//update()
	
	//댓글 삭제
	@RequestMapping(value="{rno}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService.deleteRemove(rno);//rno기준으로 댓글 삭제
			entity= new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
}
