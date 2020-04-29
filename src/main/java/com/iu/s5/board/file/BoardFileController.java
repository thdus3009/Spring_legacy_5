package com.iu.s5.board.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;

@Controller
@RequestMapping("/boardFile/**")
public class BoardFileController {

	@Autowired
	private BoardFileService boardFileService;
	
	
	
	
	//fileInsert는 summernote내에 이미지를 넣는 것의 메서드에 해당한다. (allFile해서 이미지 추가 해당x)
	//noticeWrite, qnaWrite에서 allFile눌러서 넣은 이미지를 db에 넘겨준다.
	@PostMapping("fileInsert")//url주소
	public ModelAndView fileInsert(MultipartFile files)throws Exception{//formData.append('files',files) 
		
		System.out.println(files.getOriginalFilename());
		
		ModelAndView mv = new ModelAndView();
		String fileName = boardFileService.fileInsert(files);
		//ajax를 view의 이름으로 담아주어야 한다.
		mv.addObject("result", fileName);
		mv.setViewName("common/ajaxResult");
		return mv;
	}
	
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(BoardFileVO boardFileVO)throws Exception {
		ModelAndView mv = new ModelAndView();
		boardFileVO = boardFileService.fileSelect(boardFileVO);
		mv.addObject("file", boardFileVO);
		mv.setViewName("fileDown");
		return mv;
	}
	
	
	@PostMapping("fileDelete")
	@ResponseBody
	public ModelAndView fileDelete(BoardFileVO boardFileVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardFileService.fileDelete(boardFileVO);
		/*
		 * mv.addObject("result", result); mv.setViewName("common/ajaxResult");
		 */
		return mv;
	}
	
	@PostMapping("summerDelete")
	public ModelAndView summerDelete(String fileName)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardFileService.summerDelete(fileName);
		mv.addObject("result", result);
		mv.setViewName("common/ajaxResult");
		return mv;
	}

//	@PostMapping("fileInsert")
//	public void 
	
	
}
