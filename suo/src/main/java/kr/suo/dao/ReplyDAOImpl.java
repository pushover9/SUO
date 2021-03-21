package kr.suo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.suo.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 수행 sqlSession

	@Override
	public void addReply(ReplyVO vo) {
		this.sqlSession.insert("reply_in",vo);//reply_in은 reply.xml 매퍼태그에서 설정한 유일한 insert 아이디명이다.
	}//댓글 등록

	@Override
	public List<ReplyVO> listReply(int bno) {
		
		return this.sqlSession.selectList("reply_list", bno);//mybatis에서 selectList()메서드는 하나 이상의 레코드를 검색해서 컬렉션
		//List로반환하고, reply_list는 reply.xml매퍼태그에서 설정한 유일한 select 아이디명이다.
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("reply_edit",vo);//update()메서드로 레코드를 수정하고,reply_edit는 reply.xml에서 설정한
		//유일한 update 아이디명이다.
		
	}//댓수정

	@Override
	public void delReply(int rno) {
		this.sqlSession.delete("reply_del",rno);//delete()메서드로 레코드 삭제,reply)_del은 reply.xml에서 설정한
		//유일 delete 아이디명
		
	}//댓삭

	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("reply_bno",rno);//selectOne()메서드는 단 한개의 레코드만 반환
	}//댓글번호에 ㅁ해당하는 게시물 구하기
}
