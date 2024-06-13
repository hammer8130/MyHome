package himedia.myhome.dao;

import java.util.Date;

public class UserVO {
	
	private Long no;
	private String name;
	private String password;
	private String email;
	private String gender;
	private Date createdAt;
	
	
	
	public UserVO() {
	
	}



	public UserVO(String name, String password, String email, String gender) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
	}



	public UserVO(Long no, String name, String password, String email, String gender, Date createdAt) {
		this.no = no;
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.createdAt = createdAt;
	}



	public Long getNo() {
		return no;
	}



	public void setNo(Long no) {
		this.no = no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	@Override
	public String toString() {
		return "UserVO [번호=" + no + ", 이름=" + name + ", 비밀번호=" + password + ", 이메일=" + email + ", 성별="
				+ gender + ", 생성일자=" + createdAt + "]";
	}
	
	
	

	
	
}
