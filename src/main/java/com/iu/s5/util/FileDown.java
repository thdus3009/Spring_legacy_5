package com.iu.s5.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.s5.board.file.BoardFileVO;

@Component
public class FileDown extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 이 메서드를 상속받아야 에러가 없어진다.
		BoardFileVO boardFileVO = (BoardFileVO) model.get("file");//map으로 받았기때문에 key로 꺼냄
		String fileName = boardFileVO.getFileName();
		String path="";
		if(boardFileVO.getBoard()==1) {
			path="/resources/noticeUpload";
		}else {
			path="/resources/qnaUpload";
		}
		
		path = request.getSession().getServletContext().getRealPath(path);
		
		//1.HDD에서 파일을 읽을 준비
		File file = new File(path, fileName);
		
		//2.파일의 크기 전송
		response.setContentLength((int)file.length());//file.length()은 long타입 , setContentLength은 int타입
		
		//3.다운로드시 파일 이름 인코딩(다운받을때 filename보단 oriname으로 다운받는게 더나음)
		String downName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");
		
		//4.header 설정
		response.setHeader("Content-disposition","attachment;fileName=\""+downName+"\"");//""안에 "을 쓰고 싶다면 \"형태로 써야한다.
		response.setHeader("Content-transfer-Encoding", "binary");//binary : 이진데이터
		
		//5.Stream연결 후 전송
		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fi, os);
		
		os.close();
		fi.close();
		
	}
	
	
	
}
