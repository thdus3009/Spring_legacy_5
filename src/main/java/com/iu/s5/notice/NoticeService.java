package com.iu.s5.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.s5.board.BoardService;
import com.iu.s5.board.BoardVO;

@Service
public class NoticeService implements BoardService {

@Autowired
private NoticeDAO noticeDAO;	

	@Override
	public List<BoardVO> boardList(int curPage) throws Exception {
		int startRow = (curPage-1)*10+1;
		int lastRow = curPage*10;
		Map<String , Integer> map = new HashMap<String , Integer>();
		map.put("startRow", startRow);
		map.put("lastRow",lastRow);
		
		//--------------------------------
		//1. 총 글의 갯수
		long totalCount = noticeDAO.boardCount();
		System.out.println("totalCount : " +totalCount);
		
		//2. 총 페이지 갯수(총 num이 140개면 페이지는 총14페이지)
		long totalPage = totalCount/10;
		if(totalCount%10 != 0) { //이미나눠진 페이지가 아닌 원래값에서 나머지를 확인해야 하기 때문
			totalPage++;
		}
		System.out.println(totalPage);
		
		return noticeDAO.boardList(map);
	}
	

	@Override
	public BoardVO boardSelect(long num) throws Exception {
		noticeDAO.boardHit(num);
		return noticeDAO.boardSelect(num);
	}

	//insert, update, delete >int
	
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
