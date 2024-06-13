z<%@page import="java.util.logging.Logger"%>
<%@page import="java.net.http.HttpRequest"%>
<%@page import="com.woo.dao.GuestBookDAOImpl"%>
<%@page import="com.woo.dao.GuestBookDAO"%>
<%@page import="com.woo.dao.GuestBookVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
ServletContext context = getServletContext();

String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");

GuestBookDAO dao = new GuestBookDAOImpl(dbuser,dbpass);

String password = request.getParameter("password");
Long no = Long.parseLong(request.getParameter("no"));

GuestBookVO vo = new GuestBookVO(no);

/* if(password.equals(vo.getPassword())){ */
	boolean success =  dao.deletelist(no);
if(success){
	response.sendRedirect(request.getContextPath()+"/gb?action=list");
}else{
%>	
		<h1>비밀번호 오류입니다.</h1>
		<a href="<%=request.getContextPath()%>/guestbook/index.jsp">메인으로 돌아가기</a>
<%
}
%>

