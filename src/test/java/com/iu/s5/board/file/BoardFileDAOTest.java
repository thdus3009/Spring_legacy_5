package com.iu.s5.board.file;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.member.MemberVO;

public class BoardFileDAOTest {

	@Autowired
	private BoardFileDAO boardFileDAO;
	
	@Test
	public void fileInsertTest()throws Exception{
		long fileNum=0;
		long num=0;
		String fileName="";
		String oriName="";
		int board=0;
		
		for(int i=0; i<50; i++) {
			BoardFileVO boardFileVO = new BoardFileVO();
			
			
			
			if(i==50||i==100) {
				Thread.sleep(1000);
			}
			
			
			
		}
	}
}
