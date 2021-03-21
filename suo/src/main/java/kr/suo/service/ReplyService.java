package kr.suo.service;

import java.util.List;

import kr.suo.vo.ReplyVO;

public interface ReplyService {
	void addReply(ReplyVO vo);

	List<ReplyVO> listReply(int bno);

	void updateReply(ReplyVO vo);

	
	void deleteRemove(int rno);
}
