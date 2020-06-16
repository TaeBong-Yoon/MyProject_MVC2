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
	<br>
	<br>
	<br>
	<%-- 	<%
		request.setCharacterEncoding("UTF-8");
	String message = String.valueOf(session.getAttribute("message"));

	if (message != null) {
		if (message.equals("0")) {
			out.println("<font size='6'>Modify Success!</font>");
		} else if (message.equals("1")) {
			out.println("<font size='6'>Sign Up Success! Welcome : )</font>");
			session.removeAttribute("message");
		} else {
			out.println("<font size='6'>WithDrwal Success. Thank you for using.</font>");
		}
	}
	%> --%>

	<c:set var="message" value="${sessionScope.message}" scope="session" />
	<c:choose>
		<c:when test="${message!=null && message=='0'}">
			<font size='6'>Modify Success!</font>
			<c:remove var="message" scope="session" />
		</c:when>
		<c:when test="${message!=null && message=='1'}">
			<font size='6'>Sign Up Success! Welcome : )</font>
			<c:remove var="message" scope="session" />
		</c:when>
		<c:otherwise>
			<font size='6'>WithDrwal Success. Thank you for using.</font>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<input type="button" value="Go to Main"
		onclick="javascript:window.location='MainForm.do'">

</body>
</html>