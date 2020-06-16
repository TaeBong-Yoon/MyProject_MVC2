<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.sessionID==null }">
		<br>
		<br>
		<br>
		<b><font size="6" color="darkgrey">First Page</font></b>
		<br>
		<br>
	</c:if>
	<c:if test="${sessionScope.sessionID!=null}">
		<br>
		<br>
		<br>
		<b><font size="6" color="darkgrey">Welcome!
				${sessionScope.sessionID}</font></b>
	</c:if>
</body>
</html>