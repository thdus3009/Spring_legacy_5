package com.iu.s5.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.util.Pager;

public interface BoardService {
	
	//list
	public List<BoardVO> boardList(Pager pager) throws Exception;
	
	//select
	public BoardVO boardSelect(long num) throws Exception;
	
	//insert
	public int boardWrite(BoardVO boardVO, MultipartFile [] files) throws Exception;
	
	//update
	public int boardUpdate(BoardVO boardVO, MultipartFile [] files) throws Exception;
	
	//delete //boardvo넣어도 상관x
	public int boardDelete(long num) throws Exception;
	
	//조회수는 select했을때 업데이트하기때문에 select안에 해당, 그래서 service에서 hit는 안만든다.
}
