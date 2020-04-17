package com.iu.s5.notice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;
import com.iu.s5.board.BoardVO;

public class NoticeDAOTest extends AbstractTestCase {

	@Autowired
	private NoticeDAO noticeDAO;


	@Test
	public void daoIsnull() {
		assertNotNull(noticeDAO);
	}
	
//	@Test
//	public void boardWriteTest() throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setTitle("test title");
//		noticeVO.setWriter("test writer");
//		noticeVO.setContents("test contents");
//		int result = noticeDAO.boardWrite(noticeVO);
//		
//		assertEquals(1, result); //테스트값이 1이면 성공 아니면 실패
//		
//	}
//	
//	@Test
//	public void boardDeleteTest() throws Exception{
//		int result = noticeDAO.boardDelete(5);
//		
//		assertNotEquals(0,result); //테스트값이 0이 아니면 성공
//	}
//	
//	@Test
//	public void boardUpdateTest() throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setTitle("good");
//		noticeVO.setContents("good");
//		noticeVO.setNum(6);
//		int result = noticeDAO.boardUpdate(noticeVO);
//		
//		assertNotEquals(0,result); 
//		
//	}
//	
//	@Test
//	public void boardHit() throws Exception{
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setNum(2);
//		int result = noticeDAO.boardHit(noticeVO);
//		
//		assertNotEquals(0, result);
//	}
//	@Test
//	public void boardSelectTest() throws Exception{
//		BoardVO boardVO = noticeDAO.boardSelect(2);
//		assertNotNull(boardVO);
//	}
	
	@Test
	public void boardListTest() throws Exception{
		List<BoardVO> ar = noticeDAO.boardList();
		assertNotEquals(0, ar.size());//ar에서 꺼냈을때 0과 같지 않으면 true
	}
}
