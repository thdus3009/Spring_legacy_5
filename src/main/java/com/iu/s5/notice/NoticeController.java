package com.iu.s5.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "noticeSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(long num)throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = noticeService.boardSelect(num);
		mv.addObject("vo",boardVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	//medelandview 만들어서 보내기
	@RequestMapping(value="noticeList", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv) throws Exception{
		List<BoardVO> ar = 	noticeService.boardList();
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
	
	//update
	@RequestMapping(value="noticeUpdate", method = RequestMethod.GET)
	public String boardUpdate() throws Exception {
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="noticeUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdate(BoardVO boardVO, ModelAndView mv) throws Exception{
		int result =noticeService.boardUpdate(boardVO);
		if(result>0) {
			mv.addObject("result", "게시물추가 완료");
			mv.addObject("path", "./noticeSelect?num="+boardVO.getNum());
			mv.setViewName("common/result");
		}
		return mv;
	}
	
}
