<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<jsp:include page="../include/html_header.jsp">
	<jsp:param name="title" value="로그인" />
</jsp:include>

<link href="<c:url value='/resources/css/member_login.css'/>" rel="stylesheet">
<!-- 로그인 Javascript -->
<script type="text/javascript">

	function checkValue()
	{
	    inputForm = eval("document.loginInfo");
	    if(!inputForm.email.value)
	    {
	        alert("이메일을 입력하세요");    
	        inputForm.email.focus();
	        return false;
	    }
	    if(!inputForm.passwd.value)
	    {
	        alert("비밀번호를 입력하세요");    
	        inputForm.passwd.focus();
	        return false;
	    }
	}
	
</script>

<jsp:include page="../include/body_header.jsp"></jsp:include>

	<!-- 로그인 -->
	<div id="cakeon_member_login_container">
		<form name="loginInfo" action="login_ok" 
					method="post" onsubmit="return checkValue()">
					
		<table class="cakeon_member_login_tbl">
			<tr>
				<td class="item">
					이메일
				</td>
				<td>
					<input type="text" name="email">
				</td>
			</tr>
			<tr>
				<td class="item">
					비밀번호
				</td>
				<td>
					<input type="text" name="passwd">
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인">
					<br>
					${message }
				</td>
			</tr>
			
			<tr>
				<td></td>
			</tr>
			
		</table>
		</form>
	</div>
	
<jsp:include page="../include/body_footer.jsp" ></jsp:include>