<%@page import="jsp.member.model.MemberBean"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%
	//MemberInfoAction 에서 받아온 회원정보 추출
ArrayList<MemberBean> memberList = (ArrayList<MemberBean>) request.getAttribute("memberList");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- See User 클릭시 모든 회원정보를 보여줌 -->
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}

#title {
	font-weight: bold;
}
</style>
</head>
<body>
	<br>
	<br>
	<b> <font size="6" color="darkgrey">All User's Info</font>
	</b>
	<br>
	<br>
	<br>
	<table>
		<tr align="center">
			<td id=title>ID</td>
			<td id=title>Password</td>
			<td id=title>Name</td>
			<td id=title>Gender</td>
			<td id=title>Birth</td>
			<td id=title>E-Mail</td>
			<td id=title>Phone</td>
			<td id=title>Address</td>
			<td id=title>Join Date</td>
		</tr>
		<c:set var="memberList" value="${requestScope.memberList}" />
		<c:forEach var="member" items="${memberList}">
		<tr>
			<td>${member.id}</td>
			<td>${member.password}</td>
			<td>${member.name}</td>
			<td>${member.gender}</td>
			<td>${member.birthyy}</td>
			<td>${member.mail1}</td>
			<td>${member.phone}</td>
			<td>${member.address}</td>
			<td>${member.reg}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>