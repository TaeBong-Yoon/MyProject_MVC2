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
	function checkValue() {
		if (!document.deleteform.password.value) {
			alert("You have to insert Password.");
			return false;
		}
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<br>
	<br>
	<b><font size="6" color="darkgrey">My info</font></b>
	<br>
	<br>
	<br>

	<form name="deleteform" method="post"
		action="MainForm.jsp?contentPage=member/pro/DeletePro.jsp"
		onsubmit="return checkValue()">

		<table>
			<tr>
				<td id="title">Password</td>
				<td><input type="password" name="password" maxlength="50"></td>
			</tr>
		</table>

		<br> <input type="button" value="Cancel"
			onclick="javascript:window.location='MainForm.jsp'"> <input
			type="submit" value="WithDrawl" />
	</form>

</body>
</html>