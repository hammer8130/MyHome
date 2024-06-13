package com.woo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBookDAOImpl implements GuestBookDAO {
	
	private String dbuser;
	private String dbpass;
	
	public GuestBookDAOImpl (String dbuser, String dbpass) {
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}
	
	
	
	public Connection getconnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(dburl,dbuser,dbpass);
		}catch(ClassNotFoundException e) {
			System.err.println("클래스를 로드할 수 없습니다.");
			e.printStackTrace();
		}
		return conn;
	}
		
	
	
	
	@Override
	public List<GuestBookVO> getlist() {
		List<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getconnection();
			stmt=conn.createStatement();
			String sql = "SELECT * FROM GUESTBOOK";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				String cont = rs.getString("content");
				Date date = rs.getDate("reg_date");
				
				GuestBookVO vo = new GuestBookVO(no, name, pass, cont, date);
				list.add(vo);
			}
			}catch(SQLException e) {
				System.err.println("로드하지 못하였습니다.");
				e.printStackTrace();
			}finally {
				try {
					if(conn!=null) conn.close();
					if(stmt!=null) stmt.close();
					if(rs!=null) rs.close();
				}catch(Exception e) {
					
				}
			}
		return list;
	}
	

	@Override
	public boolean insertlist(GuestBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = getconnection();
			String sql = "INSERT INTO guestbook (no,name,password,content,reg_date) VALUES (seq_guestbook_no.nextval,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			
			count = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.err.println("insert SQL 에러!");
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return count == 1; // count true 일시
	}

	@Override
	public boolean deletelist(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			conn = getconnection();
			String sql ="DELETE FROM guestbook where no = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			count = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.err.println("delete SQL 에러!");
			e.printStackTrace();
		}finally {
			try {
				if(conn!= null) conn.close();
				if(pstmt !=null) pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return count == 1;
	}

	
}
