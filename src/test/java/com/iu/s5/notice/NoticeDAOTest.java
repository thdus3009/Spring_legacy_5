package com.iu.s5.notice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;

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
	
	@Test
	public void boardUpdateTest() throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setTitle("good");
		noticeVO.setContents("good");
		noticeVO.setNum(6);
		int result = noticeDAO.boardUpdate(noticeVO);
		
		assertNotEquals(0,result); 
		
	}
}
