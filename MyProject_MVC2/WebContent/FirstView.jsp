<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("sessionID")==null){
%>
<br><br><br>
<b><font size="6" color="darkgrey">First Page</font></b>
<%} else {%>
<br><br><br>
<b><font size="6" color="darkgrey">Welcome! <%=session.getAttribute("sessionID") %></font></b>
<%} %>
</body>
</html>