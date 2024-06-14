<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Session 읽기</h3>
	
<%
// Object 객체가 반환 -> DownCasting 필수
String s1 = (String)session.getAttribute("s1");
Integer s2 = (Integer)session.getAttribute("s2");
%>

	<ul>
		<li>문자열 세션: <%=s1 %></li>
		<li>정수 세션: <%=s2 %></li>	
	</ul>
	
	<p><a href="session_delete.jsp">세션 삭제</a></p>
	<p><a href="session_write.jsp">세션 등록</a></p>
</body>
</html>