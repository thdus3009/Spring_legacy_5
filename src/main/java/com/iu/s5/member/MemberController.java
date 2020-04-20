package com.iu.s5.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;
import com.iu.s5.board.page.Pager;

@Controller //new memberController(객체생성)

@RequestMapping(value="/member/**")
public class MemberController {

	String path="";
	
	@Autowired //또는 @Inject
	private MemberService memberService;
	
	
	@RequestMapping(value="memberMyPage")
	public void memberMyPage() {
		
		
	}
	
	@RequestMapping(value= "memberDelete")
	public void memberDelete( HttpSession session) {
		System.out.println("Member Delete");
	}
	
	@RequestMapping(value="memberJoin")
	public void memberJoin(Model model) { //이런식으로 선언하면 parameter의 스트링을 숫자로 바꾸는 작업을 안해도 된다.
		String id = "test";
		model.addAttribute(id);//member/memberJoin.jsp로 전달
		//이게 제대로 작동이 안될경우 직접 key, value를 써야한다.
	}
	
	@RequestMapping(value="memberJoin",  method= RequestMethod.POST )
	public ModelAndView memberJoin2(MemberVO memberVO,ModelAndView mv)throws Exception {
		int result = memberService.memberJoin(memberVO);
		
		if(result>0) {
			mv.setViewName("redirect:../");
//			mv.addObject("result","Join Success");
//			mv.addObject("path","redirect:../");
//			mv.setViewName("common/result");
		}else {
			mv.addObject("result","Fail");
			mv.addObject("path","memberJoin");
			mv.setViewName("common/result");
		}

		return mv;
	}
	
	
	@RequestMapping(value="memberLogin")
	public void memberLogin() {
		
	}
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public String memberLogin2(MemberVO memberVO, HttpSession session)throws Exception {
		
		memberVO = memberService.memberLogin(memberVO);
		 
		//로그인 성공이면 index페이지로 이동 
		 //실패하면 로그인 실패 alert창에 띄우고 로그인 form 으로 이동
		 
		 return "redirect:../";
	}
	
	@RequestMapping(value="memberLogOut")
	public void memberLogOut()throws Exception {
		
		
	}

	
	@RequestMapping(value="memberUpdate")
	public void memberUpdate() {
		
		path="../WEB-INF/views/member/memberUpdate.jsp";
	}
	@RequestMapping(value="memberUpdate",  method=RequestMethod.POST)
	public void memberUpdate2( HttpSession session) {
		System.out.println("Member Update Post");
		
		
	}
	
	@RequestMapping(value = "memberList", method = RequestMethod.GET)
	public void memberList()throws Exception{
		
		
	}
	

	
	
	

}
