package com.sy.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	public static void main(String[] args) {
		Connection con;
		try {
			con = DBConnect.getConnection();
			System.out.println(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws Exception{
		//1.로그인 정보 4가지 기입
		
		// logon deny :
		// 1.id,pw가 틀렸을 경우 
		// 2.DB에 해당 유저가 생성되지 않은 경우 
		// 3.유저의 권한이 적용되지 않은 경우
		
		//class not found
		//1.driver명이 틀린경우
		//2.jdbc6.jar가 없는 경우
		
		String user = "scott";
		String password = "tiger";
		String url = "jdbc:oracle:thin:@192.168.56.101:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		//2.드라이버를 메모리에 로딩
		Class.forName(driver);
		
		//3. 로그인한 Connection 객체 반환
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
		
	}
	
}
