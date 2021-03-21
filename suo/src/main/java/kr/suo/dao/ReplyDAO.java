package kr.suo.dao;

import java.util.List;

import kr.suo.vo.ReplyVO;

public interface ReplyDAO {
	void addReply(ReplyVO vo);

	List<ReplyVO> listReply(int bno);

	void updateReply(ReplyVO vo);


	void delReply(int rno);

	int getBno(int rno);

}
