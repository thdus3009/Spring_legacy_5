package com.iu.s5.board.file;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.s5.util.FileSaver;
@Service
public class BoardFileService {
	@Autowired
	private BoardFileDAO boardFileDAO;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileSaver fileSaver;
	
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
