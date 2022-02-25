<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/html_header.jsp">
	<jsp:param name="title" value="게시판 수정" />
</jsp:include>

<link href="<c:url value='/resources/css/board_modify.css'/>" rel="stylesheet">
<jsp:include page="../include/body_header.jsp"></jsp:include>

<h2>게시판 글 수정</h2>

<!-- 게시판 글 수정 -->
<form action="<c:url value='/board/modify_ok'/>" method="POST">
<input type="hidden" name="id" value="${boardDTO.id }">
<input type="hidden" name="boardname" value="${boardname }">
<table class="cakeon_modify_tbl">
	<tr>
		<td class="item">
			<span id="header_item_font">
			작성자
			</span>
		</td>
		<td colspan="3">
			<input class="item_input" type="text" name="author"  value="${boardDTO.author }">
		</td>
	</tr>
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
		<td class="item">
			<span id="header_item_font">
			제목
			</span>
		</td>
		<td colspan="3">
			<input class="item_input"  type="text" name="subject" value="${boardDTO.subject }" >
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
							 name="memo"  rows="5" cols="33" id="" maxlength="4000">${boardDTO.memo }</textarea>
		</td>
	</tr>
	
	<tr>
		<td class="item">
			
			<span id="header_item_font">
			파일
			</span>
		</td>
		<td colspan="3">
			
			<!-- 파일 정보 -->
			<table class="cakeon_view_file_tbl">
				<tr>
					<td style="width:10%;">번호</td>
					<td>파일명</td>
					<td style="width:10%;">확장자</td>
					<td style="width:15%;">등록일자</td>
					<td style="width:10%;">비고</td>
				</tr>
				<!-- 파일 영역 -->
				<c:forEach var="row" items="${ boardFileInfo }">
				<tr>
					<td>
						${row.id}
					</td>
					<td>
						<a href="<c:url value='/board/download/?id=${row.id}&originFile=${row.originFile}&boardname=${boardname}'/>" target="_blank">
							${row.originFile }
						</a>
					</td>
					<td>
						${row.ext }
					</td>
					<td>
						<fmt:formatDate value="${row.regidate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<a href="<c:url value='/board/fileDelete/?id=${row.id}&article_id=${row.article_id}&boardname=${boardname}&key=${token}'/>" target="_blank">
						삭제
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<input class="cakeon_modify_btn" type="submit" value="글 수정">
		</td>
	</tr>
</table>

</form>
</body>
</html>