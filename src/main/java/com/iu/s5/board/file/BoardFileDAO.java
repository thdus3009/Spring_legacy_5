package com.iu.s5.board.file;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iu.s5.member.MemberVO;

	@Repository
	public class BoardFileDAO {
	
		@Autowired
		private SqlSession sqlSession;
		private final String NAMESPACE="com.iu.s5.board.file.BoardFileDAO.";
		
		//Select
		public BoardFileVO fileSelect(BoardFileVO boardFileVO)throws Exception{
			return sqlSession.selectOne(NAMESPACE+"fileSelect",boardFileVO);
		}
		
		//Insert   //method명은 id와 동일하게
		public int fileInsert(BoardFileVO boardFileVO) throws Exception{
			
		return sqlSession.insert(NAMESPACE+"fileInsert",boardFileVO);
	}
		//Delete
		public int fileDelete(BoardFileVO boardFileVO) throws Exception{
			return sqlSession.delete(NAMESPACE+"fileDelete",boardFileVO);
		}
		
		//FileDeleteAll
		public int fileDeleteAll(Long num) throws Exception{
			return sqlSession.delete(NAMESPACE+"fileDeleteAll",num);
		}
		
		
		//list
		public List<BoardFileVO> fileList(Long num) throws Exception{
			return sqlSession.selectList(NAMESPACE+"fileList", num);
		}
}
