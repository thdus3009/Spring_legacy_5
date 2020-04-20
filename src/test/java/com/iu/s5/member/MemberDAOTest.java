package com.iu.s5.member;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.notice.NoticeDAO;
import com.iu.s5.notice.NoticeVO;

public class MemberDAOTest {

	@Autowired  //생성자 역할
	private MemberDAO memberDAO; 
	
	/*
	 * @Test public void daoIsnull() { assertNotNull(memberDAO); }
	 */
	
	@Test
	public void memberJoinTest() throws Exception{
		String id="";
		String pw="";
		String name="";
		String phone="";
		String email="";
		
		
		for(int i=0; i<150; i++) {
		MemberVO memberVO = new MemberVO();
		
		if(i%3==0) {
			id="iu";
			pw="iu";
			name="iu";
			phone="010-0000-1111";
			email="iu"+i+"@naver.com";
		}else if(i%3==1){
			id="choa";
			pw="choa";
			name="choa";
			phone="010-0000-2222";
			email="choa@naver.com";
		}else {
			id="hani";
			pw="hani";
			name="hani";
			phone="010-0000-3333";
			email="hani@naver.com";
		}
		memberVO.setId(id+i);
		memberVO.setPw(pw+i);
		memberVO.setName(name+i);
		
		System.out.println(memberVO.getAge());
		
		
		
		memberDAO.memberJoin(memberVO);
		
		
		if(i==50||i==100) {
			Thread.sleep(1000);
		}
		
		assertNotNull(memberDAO);
		
		}
		 //테스트값이 1이면 성공 아니면 실패
	}
	
	
}
