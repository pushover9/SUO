package kr.suo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.suo.dao.BoardDAO;
import kr.suo.dao.ReplyDAO;
import kr.suo.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO replyDao;

	@Autowired
	private BoardDAO boardDao;
	
	//댓글이 추가 insert 되면, 댓글 카운터 update =>aop를 통한 트랜잭션 적용대상이 됨.
	@Transactional //트랜잭션 적용
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);
		this.boardDao.updateReply(vo.getBno(),1);//댓글 추가되면 reply_cnt컬럼에 댓글 개수가 1증가
		
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		
		return this.replyDao.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
		
	}
	///댓글삭제 카운트 1ㅅ감소
	@Transactional
	@Override
	public void deleteRemove(int rno) {
		int bno=this.replyDao.getBno(rno);//삭제될 댓글 번호에 해당 게시판 번호구함
		this.replyDao.delReply(rno);
		this.boardDao.updateReply(bno, -1);//댓글 삭제시 댓글 개수 1감소
	}
}
