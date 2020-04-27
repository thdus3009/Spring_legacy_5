package com.iu.s5.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.member.memberFile.MemberFileDAO;
import com.iu.s5.member.memberFile.MemberFileVO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private MemberFileDAO memberFileDAO;
	
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
	//하드디스크 db둘중 뭐를 먼저지워야할까 ? db먼저 지우고 하드디스크에 있는 파일을 삭제해야한다.
	
	public MemberVO memberIdCheck(MemberVO memberVO)throws Exception{
		return memberDAO.memberIdCheck(memberVO);
	}
	
	public int fileDelete(String id, HttpSession session)throws Exception{
		MemberFileVO memberFileVO = memberFileDAO.fileSelect(id);
		
		int result = memberFileDAO.fileDelete(id);
		if(result>0) {
			String path = session.getServletContext().getRealPath("/resources/memberUpload");
			result = fileSaver.deleteFile(memberFileVO.getFileName(), path);
		}
		return result;
	}
	
	public int memberUpdate(MemberVO memberVO) throws Exception{
		return memberDAO.memberUpdate(memberVO);
	}
	
	public int memberDeletes(List<String> list) throws Exception{
		return memberDAO.memberDeletes(list);
	}
	
	public int memberDelete(MemberVO memberVO) throws Exception{
		return memberDAO.memberDelete(memberVO);
	}
	
	public int memberJoin(MemberVO memberVO, MultipartFile pic, HttpSession session) throws Exception{
		//HDD에 저장 > resources/memberUpload/
		//1.파일을 HDD(하드디스크)에 저장(실제 경로 가져오기)
		String path = session.getServletContext().getRealPath("/resources/memberUpload");
		System.out.println(path);
		
		//util/FileSaver에 일 보내기
		String fileName = fileSaver.saveByUtils(pic, path);
		
		//2.파일명을 DB에 저장 (연습을 위해 2개 방법만들어봄)
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId(memberVO.getId());
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(pic.getOriginalFilename());
		
		int result = memberDAO.memberJoin(memberVO);
		result = memberFileDAO.fileInsert(memberFileVO);
		
		return result; //memberDAO.memberJoin(memberVO);
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
