package com.iu.s5.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iu.s5.board.BoardDAO;
import com.iu.s5.board.BoardVO;
import com.iu.util.Pager;

@Repository
public class NoticeDAO implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.iu.s5.notice.NoticeDAO.";
	
	
	@Override
	public long boardCount(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+"boardCount",pager);
	}
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		// 결과값이 여러개이기 때문에 return이 List타입이다. / 결과물이 하나면 One으로 
		return sqlSession.selectList(NAMESPACE+"boardList",pager);
	}

	@Override //num = mapper의 변수명
	public BoardVO boardSelect(long num) throws Exception {
	
		return sqlSession.selectOne(NAMESPACE+"boardSelect", num);
	}

	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		
		return sqlSession.insert((NAMESPACE+"boardWrite"), boardVO);//noticeMapper의 boardWrite실행 
	}
	
	@Override
	public int boardDelete(long num) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"boardDelete",num);
	}
	
	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		
		return sqlSession.update(NAMESPACE+"boardUpdate", boardVO);
	}
	
	
	@Override
	public int hitUpdate(long num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(NAMESPACE+"hitUpdate", num);
	} 

	
}
