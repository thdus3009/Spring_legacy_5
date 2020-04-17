package com.iu.s5.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
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
	@RequestMapping(value="noticeList", method = RequestMethod.GET)
	public ModelAndView boardList(@RequestParam(defaultValue = "1") int curPage, ModelAndView mv) throws Exception{
		System.out.println(curPage);
		List<BoardVO> ar = 	noticeService.boardList(curPage);
		mv.addObject("list", ar);
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
	@RequestMapping(value="noticeDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(long num, ModelAndView mv) throws Exception{
		int result = noticeService.boardDelete(num);
		if(result>0) {
			mv.addObject("result", "Delete 성공");
		}else {
			mv.addObject("result", "Delete 실패");
		}
		mv.addObject("path", "./noticeList");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	//update
	@RequestMapping(value="noticeUpdate", method = RequestMethod.GET)
	public String boardUpdate(long num, Model model) throws Exception {
		BoardVO boardVO = noticeService.boardSelect(num);//글을 하나 조회해오기
		model.addAttribute("vo",boardVO);
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="noticeUpdate", method = RequestMethod.POST) 
	//num,title,contents >noticeVO
	public ModelAndView boardUpdate(NoticeVO noticeVO, ModelAndView mv) throws Exception{
		int result =noticeService.boardUpdate(noticeVO);
		if(result>0) {
			mv.addObject("result", "게시물수정성공");
			mv.addObject("path", "./noticeList");
			mv.setViewName("common/result");
		}else {
			mv.addObject("result", "게시물수정실패");
			mv.addObject("path", "./noticeSelect?num="+noticeVO.getNum());
			mv.setViewName("common/result");
		}
		return mv;
	}
	
}
