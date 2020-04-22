package com.iu.s5.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileSaver fileSaver;
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
	
	public int memberJoin(MemberVO memberVO, MultipartFile pic, HttpSession session) throws Exception{
		//HDD에 저장 > resources/memberUpload/
		//1.실제 경로 가져오기
		String path = session.getServletContext().getRealPath("/resources/memberUpload");
		System.out.println(path);
		
		//util/FileSaver에 일 보내기
		fileSaver.saveByTransfer(pic, path);
		
		return 0; //memberDAO.memberJoin(memberVO);
	}
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberDAO.memberLogin(memberVO);
	}
	public MemberVO memberLogout(MemberVO memberVO) throws Exception{
		return memberDAO.memberLogout(memberVO);
	}
	
	
	public List<MemberVO> memberList(Pager pager)throws Exception{
		pager.makeRow();
		long totalCount = memberDAO.memberCount(pager);
		pager.makePage(totalCount);
		return memberDAO.memberList(pager);
	}
	
}
