<%@page import="java.util.List"%>
<%@page import="com.woo.dao.GuestBookDAO"%>
<%@page import="com.woo.dao.GuestBookDAOImpl"%>
<%@page import="com.woo.dao.GuestBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
ServletContext context = getServletContext();

String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<%
	GuestBookDAO dao = new GuestBookDAOImpl(dbuser,dbpass);
	List<GuestBookVO> list = dao.getlist();
	
	
%>

	<form action="<%=request.getContextPath()%>/gl" method="POST">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 ">
			</td>
		</tr>
	</table>
	<%
	for(GuestBookVO vo : list){
		int i =0;
		i++;
	%>	
	
	<table width=510 border=1>
		<tr>
			<td>[i]</td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getDate() %></td>
			<%-- <form action="<%=request.getContextPath()%>/guestbook/delete.jsp" method="POST">
			<input type="hidden" name="no" value="<%=vo.getNo()%>"> --%>
			<td>
				<a href="<%=request.getContextPath()%>/gb?no=<%=vo.getNo()%>">삭제</a>
			</td>
			</form>
		</tr>
		<tr>
			<td colspan=4><%=vo.getContent() %></td>
		</tr>
	</table>
	
	
	</form>
	<br/>
<% 
	}
%>



</body>
</html>