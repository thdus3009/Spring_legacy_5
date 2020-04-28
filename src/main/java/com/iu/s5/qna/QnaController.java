package com.iu.s5.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
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
	
	//controller에서 board를 qna로 리턴 선언 ${board}==qna
	@ModelAttribute("board")
	public String getBoard() throws Exception{
		return "qna";
	}
	
	
	@RequestMapping(value = "qnaSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(long num, ModelAndView mv)throws Exception{
		BoardVO boardVO = qnaService.boardSelect(num);
		mv.addObject("vo",boardVO); //jsp에서 뿌려주는 이름과 통일해야한다.
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	
	@GetMapping("qnaReply") //boardSelect > boardReply 할때 num 파라미터가 넘어간다.
	public ModelAndView boardReply(long num,ModelAndView mv)throws Exception{
		mv.addObject("num",num);
		mv.setViewName("board/boardReply");
		return mv;
	}
	@PostMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv, QnaVO qnaVO)throws Exception{
		int result = qnaService.boardReply(qnaVO);
		
		if(result>0) {
			mv.setViewName("redirect:../qnaList");
		}else {
			mv.addObject("result", "Reply Write Fail");
			mv.addObject("path","./qnaList");
			mv.setViewName("common/result");
		}
		
		return mv;
	}
	
	
	//medelandview 만들어서 보내기 //파라미터이름과 변수명이 다를때 >@RequestParam
//	@RequestMapping(value = "qnaList", method = RequestMethod.GET) //url정보 method방식
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager, ModelAndView mv)throws Exception{
		//curpage, search, ...>Pager에서 받아준다.
		System.out.println("kind : "+pager.getKind());
		System.out.println("search : "+pager.getSearch());
		
		 List<BoardVO> ar = qnaService.boardList(pager);
		 System.out.println(pager.getTotalPage());
		 mv.addObject("list", ar);
		 mv.addObject("pager", pager);
		 mv.setViewName("board/boardList");
		 return mv;
	}
	
	
	
	//insert(원본글 작성 / 아직 답글x)
	@GetMapping("qnaWrite")
	public String boardWrite() throws Exception {
		return "board/boardWrite";
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(QnaVO qnaVO, MultipartFile [] files, ModelAndView mv) throws Exception{
		
		int result =qnaService.boardWrite(qnaVO, files);
		String msg = "Qna Write Fail";
		
		if(result>0) {
			//성공하면 redirect 실패하면 forward
			msg = "Qna Write Success";
			
		}
		
		mv.addObject("result", msg);
		mv.addObject("path", "./qnaList");
		mv.setViewName("common/result"); 
		
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
	public String boardUpdate(QnaVO qnaVO, MultipartFile [] files)throws Exception{
		 
		int result = qnaService.boardUpdate(qnaVO,files);
		String path="";
		
		if(result>0) {
			path= "redirect:./qnaList";
		}else {
			path= "redirect:./qnaSelect?num="+qnaVO.getNum();
		}
		 
		return path;
	}
}
