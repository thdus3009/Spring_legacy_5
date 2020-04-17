package com.iu.s5.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.s5.board.BoardService;
import com.iu.s5.board.BoardVO;

@Service
public class NoticeService implements BoardService {

@Autowired
private NoticeDAO noticeDAO;	

	@Override
	public List<BoardVO> boardList() throws Exception {
		
		return noticeDAO.boardList();
	}

	@Override
	public BoardVO boardSelect(long num) throws Exception {
		noticeDAO.boardHit(num);
		return noticeDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		
		return noticeDAO.boardWrite(boardVO);
	}

	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		
		return noticeDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(long num) throws Exception {

		return noticeDAO.boardDelete(num);
	}

}
