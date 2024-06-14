<%@page import="himedia.myhome.dao.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
UserVO authUser = (UserVO)session.getAttribute("authUser");
%>
<div id="header">
      <h1>My Homepage</h1>
	<%
	if(authUser !=null){ // 로그인 성공
	%>
	<ul>
		<li><%=authUser.getName()%>님, 환영합니다.</li>	<br>
		<li><a href="<%=request.getContextPath()%>/users?a=logout">로그아웃</a></li>
      <!-- 로그인 한 사용자 -->
      <!-- 웰컴 메시지, 로그아웃 링크 -->
	</ul>
  	  <% }else{ %>
   	<ul>
      <!-- 로그인 안한 사용자 -->
      <!-- 가입링크, 로그인 폼 링크 -->
   		<li><a href="<%=request.getContextPath()%>/users?a=joinform">회원가입 링크</a></li>
   		<li><a href="<%=request.getContextPath()%>/users?a=loginform">로그인</a></li>
   	</ul>
      <% }%>
      
      	
    </div> <!-- /header -->
    
    