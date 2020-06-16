<%@page import="jsp.member.model.MemberBean"%>
<%@page import="jsp.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
}

#title {
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function changeForm(val) {
		if (val == "-1") {
			location.href = "MainForm.do";
		} else if (val == "0") {
			location.href = "MemberModifyFormAction.do";
		} else if (val == "1") {
			location.href = "DeleteForm.do";
		}
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	String id = session.getAttribute("sessionID").toString();
	MemberDAO dao = new MemberDAO();
	MemberBean memberBean = dao.getUserInfo(id);
	%>
	<br>
	<br>
	<b><font size="6" color="darkgrey">My Info</font></b>
	<br>
	<br>
	<br>
	<table>
		<tr>
			<td id="title">ID</td>
			<td><%=memberBean.getId()%></td>
		</tr>

		<tr>
			<td id="title">Password</td>
			<td><%=memberBean.getPassword()%></td>
		</tr>

		<tr>
			<td id="title">Name</td>
			<td><%=memberBean.getName()%></td>
		</tr>

		<tr>
			<td id="title">Gender</td>
			<td><%=memberBean.getGender()%></td>
		</tr>

		<tr>
			<td id="title">Birth</td>
			<td><%=memberBean.getBirthyy()%>년 <%=memberBean.getBirthmm()%>월
				<%=memberBean.getBirthdd()%>일</td>
		</tr>

		<tr>
			<td id="title">E-Mail</td>
			<td><%=memberBean.getMail1()%>@ <%=memberBean.getMail2()%></td>
		</tr>

		<tr>
			<td id="title">Phone</td>
			<td><%=memberBean.getPhone()%></td>
		</tr>
		<tr>
			<td id="title">Address</td>
			<td><%=memberBean.getAddress()%></td>
		</tr>
	</table>

	<br>
	<input type="button" value="Back" onclick="changeForm(-1)">
	<input type="button" value="Modify Info" onclick="changeForm(0)">
	<input type="button" value="WithDrwal" onclick="changeForm(1)">
</body>
</html>