package himedia.myhome.controller;

import java.io.IOException;
import java.util.List;

import com.woo.dao.GuestBookDAO;
import com.woo.dao.GuestBookDAOImpl;
import com.woo.dao.GuestBookVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "GuestBook", urlPatterns = "/gb")
public class GuestBookServlet extends BaseServlet {
	
	private static final long serialVersionUID = -3003147125073675018L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String actionName = req.getParameter("action");
		if("list".equals(actionName)) {
			GuestBookDAO dao = new GuestBookDAOImpl("himedia", "himedia");
			req.setAttribute("list", dao.getlist());
			
//			다음에 내가 처리할 요청/응답을 찾는다.
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/index.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String actionName = req.getParameter("a");
		System.out.println("doPost : " + actionName);
		
		if("deleteform".equals(actionName)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(req, resp);	
		}else if("delete".equals(actionName)) {
			GuestBookDAO dao = new GuestBookDAOImpl(id, pw);
			Long no = Long.parseLong(req.getParameter("no"));
			dao.deletelist(no);
			
			req.setAttribute("list", dao.getlist());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/delete.jsp");
			rd.forward(req, resp);
		}else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
		
		if("add".equals(actionName)) {
			GuestBookDAO dao = new GuestBookDAOImpl(id, pw);
			String name = req.getParameter("name");
			String pass = req.getParameter("pass");
			String content = req.getParameter("content");
			
			GuestBookVO vo = new GuestBookVO(name, pass, content);
			dao.insertlist(vo);
			
			req.setAttribute("list", dao.getlist());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/index.jsp");
			rd.forward(req, resp);			
		}
		else {
			
			super.doPost(req, resp);
		}
	}
	
}
