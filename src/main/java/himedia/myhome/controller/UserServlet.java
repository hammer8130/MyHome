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
		}else {
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
			
		}else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); //404 Not found
		}

	}
	
	
}
