<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${param.title }</title>
<link href="<c:url value='/resources/css/homepage.css'/>" rel="stylesheet">
<!-- <link rel="stylesheet" href="<c:url value='/resources/plugins/bootstrap-4.1.3-dist/css/bootstrap.min.css'/>"> -->
<!-- <script src="<c:url value='/resources/plugins/bootstrap-4.1.3-dist/js/bootstrap.min.js'/>" ></script> -->
<link href="<c:url value='/resources/plugins/flatResponsive/button.css'/>" rel="stylesheet" type='text/css'>