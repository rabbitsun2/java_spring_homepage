<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/html_header.jsp">
	<jsp:param name="title" value="게시판 목록" />
</jsp:include>

<link href="<c:url value='/resources/css/board_list.css'/>" rel="stylesheet">
<jsp:include page="../include/body_header.jsp"></jsp:include>

<h2>게시판</h2>
<!-- 게시판 목록 -->
<table class="cakeon_list_tbl">

	<thead>
		<tr>
			<td style="width:10%">
				<span id="cakeon_table_header_font">
				번호
				</span>
			</td>
			<td style="width:10%">
				<span id="cakeon_table_header_font">
				작성자
				</span>
			</td>
			<td>
				<span id="cakeon_table_header_font">
				제목
				</span>
			</td>
			<td style="width:15%">
				<span id="cakeon_table_header_font">
				등록일자
				</span>
			</td>
			<td style="width:10%">
				<span id="cakeon_table_header_font">
				조회수
				</span>
			</td>
		</tr>
	</thead>
	
	<tbody>
	
		<!-- 게시판 영역 -->
		<c:forEach var="row" items="${boardList }">
		<tr>
			<td>
				${row.id }
			</td>
			<td>
				${row.author }
			</td>
			<td>
				<a href="<c:url value='/board/view/${boardname }?id=${row.id }' />">${row.subject }</a>
			</td>
			<td>
				<fmt:formatDate value="${row.regidate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td>
				${row.cnt }
			</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
	<br>

<!-- 페이징 영역 -->
<div style="text-align:center">
<jsp:include page="../include/paging.jsp">
	<jsp:param name="customURL" value="${boardPagingUrl}" />
    <jsp:param name="firstPageNo" value="${boardPaging.firstPageNo}" />
    <jsp:param name="prevPageNo" value="${boardPaging.prevPageNo}" />
    <jsp:param name="startPageNo" value="${boardPaging.startPageNo}" />
    <jsp:param name="pageNo" value="${boardPaging.pageNo}" />
    <jsp:param name="endPageNo" value="${boardPaging.endPageNo}" />
    <jsp:param name="nextPageNo" value="${boardPaging.nextPageNo}" />
    <jsp:param name="finalPageNo" value="${boardPaging.finalPageNo}" />
</jsp:include>
</div>

<br>

<table class="cakeon_list_foot_tbl">
	<tr>
		<td>
				<a  class="cakeon_list_btn_blue" 
					href="<c:url value='/board/write/${boardname }'/>">글쓰기</a>
		</td>
	</tr>
	<tr>
		<td>
			<form action="<c:url value='/board/list/${boardname }'/>" method="GET">
			<input type="text" name="keyword">
				<input type="submit"  class="cakeon_list_btn_blue" value="검색">
			</form>
		</td>
	</tr>
</table>

<jsp:include page="../include/body_footer.jsp" ></jsp:include>