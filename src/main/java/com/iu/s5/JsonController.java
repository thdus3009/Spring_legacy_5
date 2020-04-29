package com.iu.s5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;
import com.iu.s5.notice.NoticeService;
import com.iu.s5.util.Pager;

@RestController //response body를 자동으로 넣어줌
@RequestMapping("json")
public class JsonController {
	@Autowired
	private NoticeService noticeService;

	@GetMapping("json1")
	@ResponseBody //return 하는 boardVO을 view로 보내지 말고 바로 response body에 담아서 바로client로 보내게 하는것
	
	public List<BoardVO> json1(Pager pager)throws Exception{
//		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = noticeService.boardSelect(259); //notice의 num 숫자
		List<BoardVO> ar = noticeService.boardList(pager);
		
// pom.xml의 jackson-databind이 아래역할을 줄여준다
		
//		String json = "{";
//		json=json+"\"num\":\""+boardVO.getNum()+"\",";
//		json=json+"\"title\":\""+boardVO.getTitle()+"\"}";
//		
//		//mv.addObject("result", "{\"name\":\"iu\"}");
//		mv.addObject("result", json);
//		mv.setViewName("common/ajaxResult");
		
		return ar; //원래 dispatcher servlet거쳐서 view로간다면 return에 boardVO못들어감 
	}
	
}
