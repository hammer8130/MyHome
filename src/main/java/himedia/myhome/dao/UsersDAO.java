package himedia.myhome.dao;

import java.util.List;

public interface UsersDAO {
	
	public List<UserVO> getList();  
	public boolean insert(UserVO vo); 
	public boolean update(UserVO vo);
	public boolean delete(Long no);
	public UserVO getUserByIdAndPw(String id, String password);  //로그인
}
