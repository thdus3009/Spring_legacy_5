package com.iu.s5.qna;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.board.BoardService;
import com.iu.s5.board.BoardVO;
import com.iu.s5.board.file.BoardFileDAO;
import com.iu.s5.board.file.BoardFileVO;
import com.iu.s5.notice.NoticeDAO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class QnaService implements BoardService {

@Autowired
private QnaDAO qnaDAO;
@Autowired
private FileSaver fileSaver;
@Autowired
private ServletContext servletContext;
@Autowired
private BoardFileDAO boardFileDAO;


	public int boardReply (BoardVO boardVO) throws Exception {
		int result = qnaDAO.boardReplyUpdate(boardVO);
		result = qnaDAO.boardReply(boardVO);
		return result;
	}
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		//10개 가져 오는 것 계산하기
		pager.makeRow();
		pager.makePage(qnaDAO.boardCount(pager));
		return qnaDAO.boardList(pager);
	}

	@Override
	public BoardVO boardSelect(long num) throws Exception {
		
		qnaDAO.hitUpdate(num);
		return qnaDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardVO boardVO, MultipartFile [] files) throws Exception {		
		//insert끝나버리기 전에 sequence 번호를 먼저 가져온다.
		int result = qnaDAO.boardWrite(boardVO);
		//파일저장되는 폴더이름
		String path = servletContext.getRealPath("/resources/uploadQna");
		
		for(MultipartFile file : files) {
			BoardFileVO boardFileVO = new BoardFileVO();
			
			String fileName = fileSaver.saveByUtils(file,path); 
			
			boardFileVO.setNum(boardVO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(2);
			
			boardFileDAO.fileInsert(boardFileVO);
		}
		
		return result;
	}

	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(long num) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.boardDelete(num);
	}	

	
}
