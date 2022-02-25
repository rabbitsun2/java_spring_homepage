<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/html_header.jsp">
	<jsp:param name="title" value="게시판 보기" />
</jsp:include>

<link href="<c:url value='/resources/css/board_view.css'/>" rel="stylesheet">
<jsp:include page="../include/body_header.jsp"></jsp:include>

<h2>게시판 글 보기</h2>

<!-- 게시판 글 보기 -->
<table class="cakeon_view_tbl">
	<tr>
		<td class="item">
			<span id="header_item_font">
			작성자
			</span>
		</td>
		<td colspan="3">
			<span id="item_font">${boardDTO.author }</span>
		</td>
	</tr>
	<tr>
		<td class="item">
			<span id="header_item_font">
			제목
			</span>
		</td>
		<td colspan="3">
			<span id="item_font">${boardDTO.subject}</span>
		</td>
	</tr>
	
	<tr>
		<td class="item">
			<span id="header_item_font">
			내용
			</span>
		</td>
		<td colspan="3">
			<span id="item_font">${boardDTO.memo }</span>
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
					<td style="width:10%">번호</td>
					<td>파일명</td>
					<td style="width:10%">확장자</td>
					<td style="width:15%">등록일자</td>
				</tr>
				<!-- 파일 영역 -->
				<c:forEach var="row" items="${ boardFileInfo }">
				<tr>
					<td>
						${row.id}
					</td>
					<td>
						<a href="<c:url value='/board/download/?id=${row.id}&originFile=${row.originFile}&boardname=${boardname}'/>">
							${row.originFile }
						</a>
					</td>
					<td>
						${row.ext }
					</td>
					<td>
						<fmt:formatDate value="${row.regidate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				</c:forEach>
			</table>
			
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<input class="cakeon_view_btn"  onclick="location.href='<c:url value='/board/modify/${boardname}?id=${id }'/>'"
						type="button" value="글수정">
			<input class="cakeon_view_btn" onclick="location.href='<c:url value='/board/delete/${boardname}?id=${id }'/>'"
						type="button" value="글삭제">
			<input class="cakeon_view_btn" onclick="location.href='<c:url value='/board/list/${boardname}'/>'"
						type="button" value="목록">
		</td>
	</tr>
</table>

<jsp:include page="../include/body_footer.jsp" ></jsp:include>

</body>
</html>