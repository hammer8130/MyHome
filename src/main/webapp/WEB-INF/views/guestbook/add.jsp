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

String name = request.getParameter("name");
String pass = request.getParameter("password");
String cont = request.getParameter("content");

GuestBookVO vo = new GuestBookVO(name,pass,cont);
GuestBookDAO dao = new GuestBookDAOImpl(dbuser,dbpass);

boolean success = dao.insertlist(vo);
if(success){
	response.sendRedirect(request.getContextPath()+"/gb?action=list");
}else{
%>
	<h1>Error</h1>
	<p>다시 코딩하세요</p>
<%	
}
%>
