<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
// session 객체 삭제
session.removeAttribute("s1");
session.removeAttribute("s2");

session.invalidate();
response.sendRedirect("session_read.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>세션 삭제</h3>
	
	<p><a href="session_write.jsp">세션 등록</a></p>
</body>
</html>