package himedia.myhome.controller;

import java.io.IOException;

import himedia.myhome.dao.UserVO;
import himedia.myhome.dao.UsersDAO;
import himedia.myhome.dao.UsersDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/users")
public class UserServlet extends BaseServlet{

	private static final long serialVersionUID = 3414074467185608852L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		a=joinform -> 가입폼 페이지로 Forward
//		a=joinsuccess -> 가입 성공 페이지로 Forward
		String actionName = req.getParameter("a");
		
		if("joinform".equals(actionName)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);	
		}
		
		else if("joinsuccess".equals(actionName)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);

		}
		else if("loginform".equals(actionName)) {
//			login form
//			로그인 실패 상황 -> result=fail
			String result = req.getParameter("result");
			if("fail".equals(result)) {
//				에러 메시지를 요청 attribute에 추가.
				req.setAttribute("errorMsg","로그인 실패!");
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		}else if("logout".equals(actionName)) {
//			로그아웃
//			세션 무효화
			HttpSession session = req.getSession();
			session.removeAttribute("authUser"); // 개별 속성 삭제
			session.invalidate();
			resp.sendRedirect(req.getContextPath()); // 홈페이지로 리다이렉트
		}
		
		else {
//			홈페이지로 redirect
			resp.sendRedirect(req.getContextPath()+"/");
		}
		
//		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		a: insert -> 회원가입
		
		String actionName = req.getParameter("a");
		if("join".equals(actionName)) {
//			가입 처리
//			form 데이터 쉿ㄴ
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			UserVO vo = new UserVO(name, password, email, gender);
			UsersDAO dao = new UsersDAOImpl(id, pw);
			
			boolean success = dao.insert(vo);
			
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
//			rd.forward(req, resp);
			if(success) {
				resp.sendRedirect(req.getContextPath()+"/users?a=joinsuccess");
			}else {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"가입실패!");
			}
			
		}else if("login".equals(actionName)) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
//			사용자 정보 질의
			UsersDAO dao = new UsersDAOImpl(id,pw);
			UserVO vo = dao.getUserByIdAndPw(email, password);
			
			if(vo!=null) { // 사용자가 있음. 성공.
//				사용자 정보를 세션에 기록
				HttpSession session = req.getSession();
				session.setAttribute("authUser", vo);
				
//				홈 화면으로 redirect
//				홈 페이지의 rootpath
				resp.sendRedirect(req.getContextPath());
			}else {
				
				if(vo==null) {
					resp.sendRedirect(req.getContextPath()+"/users?a=loginform&result=fail");
				}
			}
		}else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); //404 Not found
		}

	}
	
	
}
