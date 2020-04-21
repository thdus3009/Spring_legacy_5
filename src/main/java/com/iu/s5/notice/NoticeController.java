package com.iu.s5.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;
import com.iu.util.Pager;

//parameter를 curPage=1&perPage=10로 넘기기

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	//controller에서 board를 notice로 리턴 선언 ${board}==notice
	@ModelAttribute("board")
	public String getBoard() throws Exception{
		return "notice";
	}
	
	@RequestMapping(value = "noticeSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(long num)throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = noticeService.boardSelect(num);
		mv.addObject("vo",boardVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	//medelandview 만들어서 보내기 //파라미터이름과 변수명이 다를때 >@RequestParam
	@RequestMapping(value = "noticeList", method = RequestMethod.GET)
	public ModelAndView boardList(Pager pager, ModelAndView mv)throws Exception{
		
		System.out.println("kind : "+pager.getKind());
		System.out.println("search : "+pager.getSearch());
		
		 List<BoardVO> ar = noticeService.boardList(pager);
		 System.out.println(pager.getTotalPage());
		 mv.addObject("list", ar);
		 mv.addObject("pager", pager);
		 mv.setViewName("board/boardList");
		 return mv;
	}
	
	//insert
	@RequestMapping(value="noticeWrite", method = RequestMethod.GET)
	public String boardWrite() throws Exception {
		return "board/boardWrite";
	}
	
	@RequestMapping(value="noticeWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(NoticeVO noticeVO, ModelAndView mv) throws Exception{
		int result =noticeService.boardWrite(noticeVO);
		
		if(result>0) {
			//성공하면 redirect 실패하면 forward
			mv.setViewName("redirect:./noticeList");
			
		}else {
			mv.addObject("result", "Write Fail");
			mv.addObject("path", "./noticeList");
			mv.setViewName("common/result");
		}
		return mv;
		
	}
	
	//delete
	@RequestMapping(value = "noticeDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(long num, ModelAndView mv)throws Exception{
		int result = noticeService.boardDelete(num);
		if(result>0) {
			mv.addObject("result", "Delete Success");
		}else {
			mv.addObject("result", "Delete Fail");
		}
		mv.addObject("path", "./noticeList");
		mv.setViewName("common/result");
		return mv;
	}
	
	//update
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public String boardUpdate(long num, Model model)throws Exception{
		 BoardVO boardVO = noticeService.boardSelect(num);
		 model.addAttribute("vo", boardVO);
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public String boardUpdate(NoticeVO noticeVO)throws Exception{
		 
		int result = noticeService.boardUpdate(noticeVO);
		String path="";
		
		if(result>0) {
			path= "redirect:./noticeList";
		}else {
			path= "redirect:./noticeSelect?num="+noticeVO.getNum();
		}
		 
		return path;
	}
}