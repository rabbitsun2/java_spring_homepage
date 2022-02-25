<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/html_header.jsp">
	<jsp:param name="title" value="로그아웃" />
</jsp:include>

<link href="<c:url value='/resources/css/member_logon.css'/>" rel="stylesheet">

<jsp:include page="../include/body_header.jsp"></jsp:include>

<!-- 로그인 상태 -->
<table>
	<tr>
		<td>
			<a href="logout">로그아웃</a>
		</td>
	</tr>
</table>

</body>
</html>

<jsp:include page="../include/body_footer.jsp" ></jsp:include>