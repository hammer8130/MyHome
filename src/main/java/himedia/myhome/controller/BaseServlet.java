package himedia.myhome.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


public abstract class BaseServlet extends HttpServlet{
	
	private static final long serialVersionUID = 4259372684645773704L;
	protected String id = null;
	protected String pw = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = getServletContext();
		id = context.getInitParameter("id");
		pw = context.getInitParameter("pw");
	}
	
	
}
