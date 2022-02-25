<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<style>
	
	body{
    	margin: 0;
    	padding: 0;
	}

	.cakeon_delete_tbl{
    	width:100%;
    	border-collapse : collapse;
		border-spacing : 0;
	}
	
	.cakeon_delete_tbl td{
    	border:1px solid #e2e2e2;
    	text-align:center;
    	height:30px;
	}
	
	.cakeon_delete_tbl .item{
		background-color:#e6e6e6;
		width:10%;
	}
	
	.cakeon_delete_tbl .item_password{
		background-color:#FFFFFF;
		width:40%;
	}
	
	.cakeon_delete_tbl .item_input{
		width:100%;
		text-align:left;
	}
	
	.cakeon_delete_tbl .item_input_password{
		width:98%;
		text-align:left;
	}
	
	.cakeon_delete_tbl #header_item_font{
		font-size:12px;
		font-weight:bold;
	}
	
	.cakeon_delete_tbl .item_textarea{
		width:100%;
		text-align:left;
	}
	
	.cakeon_delete_btn{
		width:100%;
		height:40px;
		background-color:#e8e8e8;
	}
	
</style>
</head>
<body>

<h2>게시판 글 삭제</h2>

<!-- 게시판 글 삭제 -->
<form action="<c:url value='/board/delete_ok'/>" method="POST">
<input type="hidden" name="id" value="${id }">
<input type="hidden" name="boardname" value="${boardname }">
<table class="cakeon_delete_tbl">
	<tr>
		<td class="item">
			<span id="header_item_font">
			비밀번호
			</span>
		</td>
		<td colspan="3">
			<input class="item_input"  type="password" name="passwd" >
		</td>
	</tr>
	
	<tr>
		<td colspan="4">
			비밀번호를 입력하세요.
		</td>
	</tr>
	
	<tr>
		<td colspan="4">
			<input class="cakeon_delete_btn" type="submit" value="글 삭제">
		</td>
	</tr>
</table>

</form>
</body>
</html>