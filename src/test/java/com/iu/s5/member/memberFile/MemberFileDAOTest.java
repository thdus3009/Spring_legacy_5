package com.iu.s5.member.memberFile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;

public class MemberFileDAOTest extends AbstractTestCase{
	
	@Autowired
	private MemberFileDAO memberFileDAO;
	
	@Test
	public void memberInsertTest()throws Exception{
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId("choa4");
		memberFileVO.setFileName("choa4_file");
		memberFileVO.setOriName("choa4_ori");
		
		int result = memberFileDAO.fileInsert(memberFileVO);
		
		assertEquals(1, result); //들어가면 1반환!
		
	}
	
}
