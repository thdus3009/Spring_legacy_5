package com.iu.s5.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	//dispacher servlet에서 값을 보내준다.?
	
	/*
	 * @Autowired public MemberService(MemberDAO memberDAO) { this.memberDAO =
	 * memberDAO; }
	 * 
	 * 
	 * @Autowired public MemberDAO getMemberDAO() { return memberDAO; }
	 * 
	 * 
	 * @Autowired public void setMemberDAO(MemberDAO memberDAO) { this.memberDAO =
	 * memberDAO; }
	 * 
	 * 
	 */
	
	public int memberUpdate(MemberVO memberVO) throws Exception{
		return memberDAO.memberUpdate(memberVO);
	}
	
	public int memberDelete(MemberVO memberVO) throws Exception{
		return memberDAO.memberDelete(memberVO);
	}
	
	public int memberJoin(MemberVO memberVO) throws Exception{
		return memberDAO.memberJoin(memberVO);
	}
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberDAO.memberLogin(memberVO);
	}
	
}
