package himedia.myhome.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDAOImpl implements UsersDAO{
	
	private String id;
	private String pw;
	
	public UsersDAOImpl (String id, String pw) {
		this.id=id;
		this.pw=pw;
	}
	
	public Connection getConn() throws SQLException{
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			
			con=DriverManager.getConnection(dburl,id,pw);
		}catch(ClassNotFoundException e) {
			System.err.println("로그인 실패!");
			e.printStackTrace();
		}
		
		return con;
	}

	@Override
	public List<UserVO> getList() {
		Connection con = null;
		Statement stmt= null;
		ResultSet rs = null;
		List<UserVO> list = new ArrayList<UserVO>();
		
		try {
			con = getConn();
			stmt=con.createStatement();
			
			String sql = "SELECT * FROM USERS";
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				Date date = rs.getDate("created_at");
				
				UserVO vo = new UserVO(no, name, password, email, gender, date);
				list.add(vo);
			}
			}catch(SQLException e) {
				System.err.println("불러오기 실패");
				e.printStackTrace();
			}finally {
				try {
					if(con!=null) con.close();
					if(stmt!=null) stmt.close();
					if(rs!=null) rs.close();
				}catch(Exception e) {
					
			}

		}
		return list;
	}

	@Override
	public boolean insert(UserVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int insertCount =0 ;
		try {
			con=getConn();
			String sql = "INSERT INTO users (no, name, password, email, gender,created_at) VALUES(seq_users_pk.nextval, ?, ?, ?, ?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGender());
			
			insertCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.err.println("insert 쿼리 불러오기 실패");
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)con.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return insertCount==1;
	}

	@Override
	public boolean update(UserVO vo) {
		
//		  Connection con = null; 
//		  PreparedStatement pstmt = null; 
//		  int updateCount = 0;
//		  
//		  try { 
//			  con=getConn(); 
//			  String sql ="" 
//			}catch(SQLException e) {
//				System.err.println("Update 실패");
//				e.printStackTrace();
//			}finally {
//				
//			}
		
		  return false;
		 
	}

	@Override
	public boolean delete(Long no) {
		
		return false;
	}

	@Override
	public UserVO getUserByIdAndPw(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO vo = new UserVO();
		
		try {
			conn=getConn();
			String sql = "SELECT no, name, email, gender FROM users " +
					"WHERE email=? AND password = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email2 = rs.getString(3);
				String gender = rs.getString(4);
				vo = new UserVO(no, name, null, email2, gender, null);
			}
		}catch(SQLException e) {
			System.err.println("Update 실패!");
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	


	

}
