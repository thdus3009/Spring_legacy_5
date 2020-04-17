package com.iu.s5.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	
	//medelandview 만들어서 보내기
	@RequestMapping(value="noticeList", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mv) throws Exception{
		List<BoardVO> ar = 	noticeService.boardList();
		mv.addObject("list", ar);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	
}
