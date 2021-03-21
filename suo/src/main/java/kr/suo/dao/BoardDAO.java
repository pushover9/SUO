package kr.suo.dao;

import java.util.HashMap;
import java.util.List;

import kr.suo.vo.BoardVO;


public interface BoardDAO {

	void insertBoard(BoardVO b);

	List<BoardVO> getBoardList(BoardVO b);

	int getTotalCount();

	BoardVO getCont(int bno);

	void editBoard(BoardVO eb);

	void delBoard(int bno);

	void updateHit(int bno);
	
	List<BoardVO> getCateList(BoardVO b);

	int getCateCount(BoardVO b);

	void updateReply(int bno, int i);


	List<BoardVO> getFileList(int bno);

	void fileUpload(String originalfileName, String saveFileName, long fileSize);

}
