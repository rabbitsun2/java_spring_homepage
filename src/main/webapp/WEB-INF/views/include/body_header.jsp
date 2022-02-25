<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
</head>
<body>

<!-- 회원 영역 -->
<table class="cakeon_web_tbl">
	<tr>
		<td>
			<div id="membership">
				<ul>
					<li><a href="">회원가입</a></li>
					
					<%
						HttpSession session = request.getSession();
						if (session.getAttribute("res") == null ){
					%>
					<li><a href="<c:url value='/member/login'/>">로그인</a></li>
					<%
						}else{
					%>
					<li><a href="<c:url value='/member/logout'/>">로그아웃</a></li>
					<% } %>
					<li><a href="">소개</a></li>
				</ul>
			</div>
		</td>
	</tr>
	<!-- 로고 -->
	<tr>
		<td style="text-align:left; padding-top:20px;">
			<div id="cakeon_header_logo">
				<a href="<c:url value='/'/>">
					<img src="<c:url value='/resources/images/logo_small.jpg'/>" alt="로고">
				</a>
			</div>
		</td>
	</tr>
</table>

<!-- 메뉴 -->
<div id="topMenu">
	<ul>
		<li><a href="">게시판1</a></li>
		<li><a href="">게시판2</a></li>
		<li><a href="">게시판3</a></li>
		<li><a href="">방명록</a></li>
		<li><a href="">github</a></li>
	</ul>
</div>

<!-- 본문 -->
<table class="cakeon_web_tbl">
	<tr>
		<td>