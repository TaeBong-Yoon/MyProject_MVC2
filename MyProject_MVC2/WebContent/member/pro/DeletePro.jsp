<%@page import="jsp.member.model.MemberDAO"%>
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
		request.setCharacterEncoding("UTF-8");
	String id = (String) session.getAttribute("sessionID");
	String pw = request.getParameter("password");

	MemberDAO dao = new MemberDAO();
	int check = dao.deleteMember(id, pw);

	if (check == 1) {
		session.invalidate();
	%>
	<br>
	<br>
	<b><font size="4" color="gray">User Info Delete Successfully</font></b>
	<br>
	<br>
	<br>

	<input type="button" value="Confilm"
		onclick="javascript:window.location='MainForm.jsp'">

	<%
		} else {
	%>
	<script>
		alert("Wrong Password.");
		history.go(-1);
	</script>
	<%
		}
	%>

</body>
</html>