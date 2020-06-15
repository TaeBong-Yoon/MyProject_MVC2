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

#title{
font-weight:bold;
}
</style>

<script type="text/javascript">
	function goFirstForm() {
		location.href = "MainForm.jsp";
	}
	function checkValue(){
		inputForm = eval("document.loginInfo");
		if(!inputForm.id.value){
			alert("Input ID");
			inputForm.id.focus();
			return false;
		}
		if(!inputForm.password.value){
			alert("Input Password");
			inputForm.password.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<div id="wrap">
		<br> <br> <b> <font size="6" color="darkgrey">
				Sign In</font>
		</b> <br> <br> <br>
		<form name="loginInfo" method="POST" action="member/pro/SignInPro.jsp"
			onsubmit="return checkValue()">
			<br> <br>
			<table>
				<tr>
					<td id="title">I D </td>
					<td><input type="text" name="id" maxlength=50></td>
				</tr>
				<tr>
					<td id="title">P/W </td>
					<td><input type="password" name="password" maxlength=50></td>
				</tr>
			</table>
			<br>
			<br>
				<input type="submit" value="Sign In" /> <input type="button"
					value="Cancel" onclick="goFirstForm()" />
		</form>
		
		<%
		String message = request.getParameter("message");
		if(message != null && message.equals("0")){
			out.println("<br>");
			out.println("<font color='red' size='5'>Check your Password!</font>");
		} else if(message != null && message.equals("-1")){
			out.println("<br>");
			out.println("<font color='red' size='5'>Check your ID!</font>");
		}
		%>
	</div>
</body>
</html>