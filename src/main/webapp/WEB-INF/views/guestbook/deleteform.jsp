<%@page import="com.woo.dao.GuestBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
ServletContext context = getServletContext();

String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");
%>
<!DOCTYPE html>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>방명록</title>
</head>
<%
	GuestBookVO vo = new GuestBookVO();
%>

<body>
	<h3>나는 딜리트 폼</h3>
	<%-- <form method="post" action="<%=request.getContextPath()%>/gb<%=request.getParameter("no") %>">
	<input type='hidden' name="id" value="<%=vo.getPassword()%>"> --%>
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="확인"></td>
			<td>
			
			<a href="<%=request.getContextPath() %>/gb">메인으로 돌아가기</a></td>
		</tr>
	</table>
	<!-- </form> -->
</body>

</html>