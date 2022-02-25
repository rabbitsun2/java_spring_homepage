<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<jsp:include page="../include/html_header.jsp">
	<jsp:param name="title" value="게시판 글쓰기" />
</jsp:include>

<link href="<c:url value='/resources/css/board_write.css'/>" rel="stylesheet">
<jsp:include page="../include/body_header.jsp"></jsp:include>

<h2>게시판 글쓰기</h2>

<!-- 게시판 글쓰기 -->
<form action="<c:url value='/board/write_ok'/>" method="post" 
			enctype="multipart/form-data">
			
<input type="hidden" name="boardname" value="${boardname }">
<table class="cakeon_write_tbl">
	<tr>
		<td class="item">
			<span id="header_item_font">
			작성자
			</span>
		</td>
		<td colspan="3">
			<input class="item_input" type="text" name="author" >
		</td>
	</tr>
	<tr>
		<td class="item">
			<span id="header_item_font">
			비밀번호
			</span>
		</td>
		<td class="item_password">
			<input class="item_input_password"  type="password" name="passwd1" >
		</td>
		<td class="item">
			<span id="header_item_font">
			비밀번호 확인
			</span>
		</td>
		<td class="item_password">
			<input class="item_input_password"  type="password" name="passwd2" >
		</td>
	</tr>
	<tr>
		<td class="item">
			<span id="header_item_font">
			제목
			</span>
		</td>
		<td colspan="3">
			<input class="item_input"  type="text" name="subject" >
		</td>
	</tr>
	
	<tr>
		<td class="item">
			<span id="header_item_font">
			내용
			</span>
		</td>
		<td colspan="3">
			<textarea class="item_textarea"
							 name="memo"  rows="5" cols="33" id="" maxlength="4000"></textarea>
		</td>
	</tr>
	
	<tr>
		<td class="item">
			<span id="header_item_font">
			첨부
			</span>
		</td>
		<td colspan="3">
			<input type="file" name="multiFile" multiple>
		</td>
	</tr>
	
	<tr>
		<td colspan="4">
			<button class="cakeon_write_btn" type="submit">글 작성</button>
		</td>
	</tr>
</table>
</form>

<jsp:include page="../include/body_footer.jsp" ></jsp:include>

</body>
</html>