<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%
	HttpSession session = request.getSession();
	Object res = session.getAttribute("res");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정도윤 - 홈페이지</title>

<link href="<c:url value='/resources/css/homepage.css'/>" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
						if (session.getAttribute("res") == null ){
					%>
						<li><a href="<c:url value='/member/login'/>">로그인</a></li>
					<% }else{ %>					 
						<li><a href="<c:url value='/member/logout'/>">로그아웃</a></li>
					<% } %>					
					<li><a href="">소개</a></li>
				</ul>
			</div>
		</td>
	</tr>
	<!-- 로고 -->
	<tr>
		<td style="text-align:left; ">
			<div id="cakeon_home_header_logo">
				<a href="<c:url value='/'/>">
					<img src="<c:url value='/resources/images/logo.jpg'/>">
				</a>
			</div>
		</td>
	</tr>
</table>

<!-- 메뉴 -->
<div id="topMenu">
	<ul>
		<li><a href="<c:url value='/board/list/hello'/>">게시판1</a></li>
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
			asdfasdf
		</td>
	</tr>
</table>

<!-- 하단 영역 -->
<table class="cakeon_web_tbl">
	<tr>
		<td>
			<div id="footerMenu">
				<ul>
					<li><a href="">소개</a></li>
					<li><a href="">개발자센터</a></li>
					<li><a href="">자주묻는질문</a></li>
				</ul>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			2022. copyright &copy;정도윤
		</td>
	</tr>
</table>

</body>
</html>