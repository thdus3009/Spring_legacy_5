package com.iu.s5.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;
import com.iu.s5.member.memberFile.MemberFileVO;
import com.iu.s5.util.Pager;

@Controller //new memberController(객체생성)

@RequestMapping(value="/member/**")
public class MemberController {

	String path="";
	
	@Autowired //또는 @Inject
	private MemberService memberService;
	
	
	@GetMapping("fileDelete")
	public String fileDelete(HttpSession session) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberService.fileDelete(memberVO.getId(),session);
		return "redirect:./memberMyPage";
	}
	
	@RequestMapping(value="memberMyPage")
	public void memberMyPage(HttpSession session, Model model)throws Exception {
		MemberVO memberVO = (MemberVO) session.getAttribute("member"); // object타입을 MemberVO로 형변환
		MemberFileVO memberFileVO =  memberService.fileSelect(memberVO.getId());
		model.addAttribute("file", memberFileVO);
		
	}
	
	@RequestMapping(value= "memberDelete")
	public ModelAndView memberDelete( HttpSession session, ModelAndView mv) throws Exception {
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		int result = memberService.memberDelete(memberVO);
		if(result>0) {
			session.invalidate();
			mv.addObject("result","Delete Success");
			mv.addObject("path","../");
			mv.setViewName("common/result");
		}else {
			mv.addObject("result", "Delete Fail");
			mv.addObject("path", "../");
			mv.setViewName("common/result");
		}
		return mv;
	}
	
	@RequestMapping(value="memberJoin")
	public void memberJoin(Model model) { //이런식으로 선언하면 parameter의 스트링을 숫자로 바꾸는 작업을 안해도 된다.
		String id = "test";
		model.addAttribute(id);//member/memberJoin.jsp로 전달
		//이게 제대로 작동이 안될경우 직접 key, value를 써야한다.
	}
	
	@RequestMapping(value="memberJoin",  method= RequestMethod.POST ) //file > multipartfile로 만든다.(파라미터명과 동일하게)
	public ModelAndView memberJoin2(MemberVO memberVO , MultipartFile pic , ModelAndView mv, HttpSession session)throws Exception {
		
		System.out.println("파일업로드 시 실제 이름 : " +pic.getOriginalFilename()); //filename이 찍힌다 (nn.jpg > 이런식으로)
		System.out.println("파일의 파라미터이름 : "+pic.getName());
		System.out.println("파일 용량 (단위 byte) : "+pic.getSize()); //파일용량 > root-context에서 조정가능
		System.out.println("파일 형식 : "+pic.getContentType()); //image인데 확장자가 jpg이다, text인데 html이다... 등의 정보를 알려줌

		//파일을 서버내에 영구히 저장해야함 > 파일은 number,varchar2,date 타입이아니다.
		//BLOB(최대 2기가) > DB에서 이진데이터의 바이트를 저장하는것
		
		int result = memberService.memberJoin(memberVO, pic, session);
		
		if(result>0) {
			mv.addObject("result","Join Success");
			mv.addObject("path","../");
			mv.setViewName("common/result");
		}else {
			mv.addObject("result","Join Fail");
			mv.addObject("path","memberJoin");
			mv.setViewName("common/result");
		}

		return mv;
	}
	
	
	@RequestMapping(value="memberLogin")
	public void memberLogin(@CookieValue(value = "cId", required =false) String cId , Model model) {
		System.out.println(cId);
		//model.addAttribute("cId",cId);
	}
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public ModelAndView memberLogin2(ModelAndView mv, MemberVO memberVO,String remember, HttpSession session, HttpServletResponse response)throws Exception {
		//remember me를 누르고 sysout안에 remember를 받아오면 remember이 출력되고 안누르면 출력이 안된다.
		Cookie cookie = new Cookie("cid", "");
		
		if(remember != null) {
//			cookie = new Cookie("cid", memberVO.getId());
			cookie.setValue(memberVO.getId());
		}

//		cookie.setMaxAge(30);//cookie가 계속 쌓이면 안되니까 일정시간이 지나면 사라지게 되어있다.//쿠키지우고 싶으면 0으로 하면됨
//		cookie.setValue(newValue);
		response.addCookie(cookie);
		
		memberVO = memberService.memberLogin(memberVO);
		 System.out.println(memberVO);
		 if(memberVO != null) {
			 session.setAttribute("member", memberVO);
			 mv.setViewName("redirect:../");//ar,pager를 못받아 넘기기 때문에 재검색해준다.
		 }else {
			 mv.addObject("result", "Login Fail");
			 mv.addObject("path", "./memberJoin");
			 mv.setViewName("common/result");
		 }
		 
		//로그인 성공이면 index
		//로그인 실패 하면 로그인 실패 alert login form 이동		 
				 
				 
		return mv;
	}
	
	@RequestMapping(value="memberLogOut")
	public String memberLogOut(HttpSession session)throws Exception {
		session.invalidate();
		return "redirect:../";	
		}

	
	@RequestMapping(value="memberUpdate")
	public void memberUpdate() {
		
		path="../WEB-INF/views/member/memberUpdate.jsp";
	}
	@RequestMapping(value="memberUpdate",  method=RequestMethod.POST)
	public ModelAndView memberUpdate2( HttpSession session, MemberVO memberVO, ModelAndView mv)throws Exception{
		String id = ((MemberVO)session.getAttribute("member")).getId(); //현재 로그인하고 있는 아이디의 수정이기때문에 session을 쓴다.
		memberVO.setId(id);
		
		int result = memberService.memberUpdate(memberVO);
	//변형되면 1, 변형되지않으면 0
		if(result>0) {
			session.setAttribute("member", memberVO);//MemberUpdate.jsp에서 ${member}이렇게 쓰인다.
			mv.addObject("result","Update Success");
			mv.addObject("path", "./memberMyPage"); //성공했으면 변경된 값을 재확인 하기 위해 redirect한다. 
			mv.setViewName("common/result");
		}else {
			mv.addObject("result","Update Fail");
			mv.addObject("path","./memberPage"); //실패했으니 다시 수정하기위해 현재페이지그대로 둔다. (redirect x)
			mv.setViewName("common/result");
		}
		return mv;
	}
	
	
	@GetMapping("memberList")
	public ModelAndView memberList(Pager pager, ModelAndView mv)throws Exception{
		
		 List<MemberVO> ar = memberService.memberList(pager);
		 
		 mv.addObject("list", ar);
		 mv.addObject("pager", pager);
		 mv.setViewName("member/memberList");
		 return mv;
	}
	

	
	
	

}
