package com.iu.s5.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;
import com.iu.s5.notice.NoticeService;
import com.iu.s5.notice.NoticeVO;
import com.iu.s5.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	//controller에서 board를 notice로 리턴 선언 ${board}==notice
	@ModelAttribute("board")
	public String getBoard() throws Exception{
		return "qna";
	}
	
	
	@RequestMapping(value = "qnaSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(long num)throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = qnaService.boardSelect(num);
		mv.addObject("vo",boardVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	//medelandview 만들어서 보내기 //파라미터이름과 변수명이 다를때 >@RequestParam
	@RequestMapping(value = "qnaList", method = RequestMethod.GET)
	public ModelAndView boardList(Pager pager, ModelAndView mv)throws Exception{
		
		System.out.println("kind : "+pager.getKind());
		System.out.println("search : "+pager.getSearch());
		
		 List<BoardVO> ar = qnaService.boardList(pager);
		 System.out.println(pager.getTotalPage());
		 mv.addObject("list", ar);
		 mv.addObject("pager", pager);
		 mv.setViewName("board/boardList");
		 return mv;
	}
	
	//insert
	@RequestMapping(value="qnaWrite", method = RequestMethod.GET)
	public String boardWrite() throws Exception {
		return "board/boardWrite";
	}
	
	@RequestMapping(value="qnaWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(QnaVO qnaVO, ModelAndView mv) throws Exception{
		int result =qnaService.boardWrite(qnaVO);
		
		if(result>0) {
			//성공하면 redirect 실패하면 forward
			mv.setViewName("redirect:./qnaList");
			
		}else {
			mv.addObject("result", "Write Fail");
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/result");
		}
		return mv;
		
	}
	
	//delete
	@RequestMapping(value = "qnaDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(long num, ModelAndView mv)throws Exception{
		int result = qnaService.boardDelete(num);
		if(result>0) {
			mv.addObject("result", "Delete Success");
		}else {
			mv.addObject("result", "Delete Fail");
		}
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result");
		return mv;
	}
	
	//update
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.GET)
	public String boardUpdate(long num, Model model)throws Exception{
		 BoardVO boardVO = qnaService.boardSelect(num);
		 model.addAttribute("vo", boardVO);
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.POST)
	public String boardUpdate(QnaVO qnaVO)throws Exception{
		 
		int result = qnaService.boardUpdate(qnaVO);
		String path="";
		
		if(result>0) {
			path= "redirect:./qnaList";
		}else {
			path= "redirect:./qnaSelect?num="+qnaVO.getNum();
		}
		 
		return path;
	}
}
