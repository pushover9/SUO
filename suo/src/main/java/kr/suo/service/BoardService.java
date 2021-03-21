package kr.suo.service;

import java.util.List;

import kr.suo.vo.BoardVO;




public interface BoardService {

	void insertBoard(BoardVO b);

	List<BoardVO> getBoardList(BoardVO b);

	int getTotalCount();

	BoardVO getCont(int bno);

	void editBoard(BoardVO eb);

	BoardVO delBoard(int bno);

	List<BoardVO> getCateList(BoardVO b);

	int getCateCount(BoardVO b);


	List<BoardVO> getFileList(int bno);

	void fileUpload(String originalfileName, String saveFileName, long fileSize);

}
