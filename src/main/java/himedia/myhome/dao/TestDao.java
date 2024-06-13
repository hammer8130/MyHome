package himedia.myhome.dao;

import java.util.Iterator;
import java.util.List;



public class TestDao {
	public static void main(String[] args) {
		//	목록 출력
		list();
		
		//	회원 정보 입력
//		insert("홍길동", "1234", "hong@hwalbin.org", "M");
//		insert("이영희", "4321", "younghee@lee.name", "F");
		
		//	목록 출력
		list();
	}
	
private static void testGetUserByIdAndPassword(String email, String password) {
		
		UsersDAO dao = new UsersDAOImpl("himedia", "himedia");
		UserVO vo = dao.getUserByIdAndPw(email, password);
		
		System.out.println("USER " + (vo == null ? "NOT FOUND": "FOUND"));
		System.out.println("USER INFO:" + vo);
		
	}
	private static void insert(String name, String password, String email, String gender) {
		UsersDAO dao = new UsersDAOImpl("himedia", "himedia");
		
		UserVO vo = new UserVO(name, password, email, gender);
		
		boolean success = dao.insert(vo);
		
		System.out.println(name + " INSERT " + (success ? "SUCCESS": "FAILED"));
	}

	public static void list() {
		UsersDAO dao = new UsersDAOImpl("himedia", "himedia");
		
		List<UserVO> list = dao.getList();
		Iterator<UserVO> it = list.iterator();
		
		System.out.println("====================");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("====================");
	}
	
	
}
