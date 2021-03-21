package kr.suo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.suo.dao.BoardDAO;
import kr.suo.vo.BoardVO;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public void insertBoard(BoardVO b) {
		this.boardDao.insertBoard(b);
		
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.boardDao.getBoardList(b);
	}

	
	@Override
	public int getTotalCount() {
		
		return this.boardDao.getTotalCount();
	}

	@Override
	public BoardVO getCont(int bno) {
		this.boardDao.updateHit(bno);//조회수 증가.
		return this.boardDao.getCont(bno);
	}

	@Override
	public void editBoard(BoardVO eb) {
		// TODO Auto-generated method stub
		this.boardDao.editBoard(eb);
	}

	@Override
	public BoardVO delBoard(int bno) {
		this.boardDao.delBoard(bno);
		return null;
	}

	@Override
	public List<BoardVO> getCateList(BoardVO b) {
		
		return this.boardDao.getCateList(b);
	}

	@Override
	public int getCateCount(BoardVO b) {
		
		return this.boardDao.getCateCount(b);
	}



	@Override
	public List<BoardVO> getFileList(int bno) {
		return boardDao.getFileList(bno);
	}

	@Override
	public void fileUpload(String originalfileName, String saveFileName, long fileSize) {
		this.boardDao.fileUpload(originalfileName,saveFileName,fileSize);
	}

}

