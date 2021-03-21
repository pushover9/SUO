package kr.suo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.suo.vo.BoardVO;


@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("insert_b", b);
		
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		
		return this.sqlSession.selectList("list_b", b);
	}

	@Override
	public int getTotalCount() {
		
		return this.sqlSession.selectOne("count_b");
	}

	@Override
	public BoardVO getCont(int bno) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("b_cont",bno);
	}

	@Override
	public void editBoard(BoardVO eb) {
		// TODO Auto-generated method stub
		this.sqlSession.update("b_edit",eb);
	}

	@Transactional
	@Override
	public void delBoard(int bno) {
		// TODO Auto-generated method stub
		this.sqlSession.delete("board_img_del",bno);
		this.sqlSession.delete("reply_board_del",bno);
		this.sqlSession.delete("b_del",bno);
	}

	@Override
	public void updateHit(int bno) {
		// TODO Auto-generated method stub
		this.sqlSession.update("b_hit",bno);
	}

	@Override
	public List<BoardVO> getCateList(BoardVO b) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("b_cate",b);
	}

	@Override
	public int getCateCount(BoardVO b) {
		
		return this.sqlSession.selectOne("count_cate",b);
	}

	@Override
	public void updateReply(int bno, int count) {
		System.out.println("boarddaoimpl"+count);
		Map<String,Object> pm = new HashMap<>();
		pm.put("bno",  bno);
		pm.put("count", count);
		this.sqlSession.update("upReplyCnt",pm);
		
	}

	
	@Override
	public List<BoardVO> getFileList(int bno) {
		return this.sqlSession.selectList("cont_img",bno);
	}

	@Transactional
	@Override
	public void fileUpload(String originalfileName, String saveFileName, long fileSize) {
		
		HashMap<String, Object> hm = new HashMap<>();
	    hm.put("originalfileName", originalfileName);
	    hm.put("saveFileName", saveFileName);
	    hm.put("fileSize", fileSize);
	  
	    sqlSession.insert("uploadFile",hm);
		
	}

	
	
}
