package com.iu.s5.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.board.BoardService;
import com.iu.s5.board.BoardVO;
import com.iu.s5.board.file.BoardFileDAO;
import com.iu.s5.board.file.BoardFileVO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class NoticeService implements BoardService {

@Autowired
private NoticeDAO noticeDAO;	
@Autowired
private FileSaver fileSaver;
@Autowired
private ServletContext servletContext;
@Autowired
private BoardFileDAO boardFileDAO;


	@Override //pager안에 세팅됨
	public List<BoardVO> boardList(Pager pager) throws Exception {
//		int startRow = (curPage-1)*10+1;
//		int lastRow = curPage*10;
//		Map<String , Integer> map = new HashMap<String , Integer>();
//		map.put("startRow", startRow);
//		map.put("lastRow",lastRow);
		
		
		pager.makeRow();
		long totalCount = noticeDAO.boardCount(pager);
		pager.makePage(totalCount);
		
		return noticeDAO.boardList(pager);
	}

	@Override
	public BoardVO boardSelect(long num) throws Exception {
	
		noticeDAO.hitUpdate(num);
		return noticeDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardVO boardVO, MultipartFile [] files) throws Exception {
		String path = servletContext.getRealPath("/resources/uploadNotice");
		System.out.println(path);
		
		//sequence의 번호 받아오기
		boardVO.setNum(noticeDAO.boardNum());
		
		//notice table insert
		int result = noticeDAO.boardWrite(boardVO);
		
		for(MultipartFile file : files) {
			BoardFileVO boardFileVO = new BoardFileVO();
			
			String fileName = fileSaver.saveByUtils(file, path);
			
			boardFileVO.setNum(boardVO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(1);
		//HDD에 파일을 저장하고 boardFile table insert
			boardFileDAO.fileInsert(boardFileVO);
		}
		
		return result; 
	}

	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {

		return noticeDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(long num) throws Exception {

		return noticeDAO.boardDelete(num);
	}
}
