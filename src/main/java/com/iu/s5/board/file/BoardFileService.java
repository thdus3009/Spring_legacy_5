package com.iu.s5.board.file;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.util.FileSaver;
@Service
public class BoardFileService {
	@Autowired
	private BoardFileDAO boardFileDAO;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileSaver fileSaver;
	
	
	public int summerDelete(String fileName)throws Exception{
		String path = servletContext.getRealPath("/resources/summer");
		return fileSaver.deleteFile(fileName, path);
	}
	
	public String fileInsert(MultipartFile files)throws Exception{//파일을 저장할때는 MultipartFile사용
		String path = servletContext.getRealPath("/resources/summer");
		System.out.println(path);
		path = fileSaver.saveByTransfer(files, path);
		path = servletContext.getContextPath()+"/resources/summer/"+path;
		return path;
		
		
	}
	
	public BoardFileVO fileSelect(BoardFileVO boardFileVO)throws Exception{
		return boardFileDAO.fileSelect(boardFileVO);
	}
	
	public int fileDelete(BoardFileVO boardFileVO)throws Exception{
		
		boardFileVO = boardFileDAO.fileSelect(boardFileVO);
		int result = boardFileDAO.fileDelete(boardFileVO);
		//1.hdd에 삭제
		String board = "noticeUpload";
		if(boardFileVO.getBoard()==2) {
			board="qnaUpload";
		}
		String path = servletContext.getRealPath("/resources/"+board);
		fileSaver.deleteFile(boardFileVO.getFileName(), path);
		return result;
	};
}
