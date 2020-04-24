package com.iu.s5.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iu.s5.board.BoardVO;
import com.iu.s5.util.Pager;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.iu.s5.member.MemberDAO.";
	
	public MemberVO memberIdCheck(MemberVO memberVO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memberIdCheck", memberVO);
	}

	public long memberCount(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+"memberCount",pager);
	}
	
	//list 
	public List<MemberVO> memberList(Pager pager) throws Exception {
		// 결과값이 여러개이기 때문에 return이 List타입이다. / 결과물이 하나면 One으로 
		return sqlSession.selectList(NAMESPACE+"memberList", pager);
	}
	
	
	
	//update
	public int memberUpdate(MemberVO memberVO) throws Exception{
		
		//String sql = "update member set name=?,age=?,phone=?,email=? where id=?";
	
		return sqlSession.update(NAMESPACE+"memberUpdate",memberVO);
		
	}
	
	
	//delete
	public int memberDelete(MemberVO memberVO) throws Exception{
		//String id 해도 되지만 dto받아서 get으로 받아올수있다.
		
		//String sql = "delete member where id=?";
		
		return sqlSession.delete(NAMESPACE+"memberDelete",memberVO);
	}
	
	//join
	public int memberJoin(MemberVO memberVO) throws Exception{
	
		//String sql = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?)";
		
		return sqlSession.insert((NAMESPACE+"memberJoin"),memberVO);
	}
	
	//login
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		
		//String sql = "select * from member where id=? and pw=?";
		
		return sqlSession.selectOne(NAMESPACE+"memberLogin",memberVO);
	}
	
	public MemberVO memberLogout(MemberVO memberVO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memberLogout",memberVO);
	}
	
	//MyPage
	
}
