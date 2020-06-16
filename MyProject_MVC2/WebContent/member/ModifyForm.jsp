<%@page import="jsp.member.model.MemberBean"%>
<%@page import="jsp.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%-- <%
	//MemberInfoAction에서 넘긴 회원 정보 추출
MemberBean member = (MemberBean) request.getAttribute("memberInfo");
%> --%>

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
	function init() {
		setComboValue("${member.mail2}");
	}

	function setComboValue(val) {
		var selectMail = document.getElementById('mail2'); // select 아이디를 가져온다.
		for (i = 0, j = selectMail.length; i < j; i++) // select 하단 option 수만큼 반복문 돌린다.
		{
			if (selectMail.options[i].value == val) // 입력된값과 option의 value가 같은지 비교
			{
				selectMail.options[i].selected = true; // 같은경우라면 체크되도록 한다.
				break;
			}
		}
	}

	function checkValue() {
		if (!document.userInfo.password.value) {
			alert("Check your Password");
			return false;
		}
	}
</script>
</head>
<body onload="init()">
	<br>
	<br>
	<b><font size="6" color="darkgrey">Modify User Info</font></b>
	<br>
	<br>
	<br>
	<c:set var="member" value="${requestScope.memberInfo}" />

	<form method="post" action="MemberModifyAction.do" name="userInfo"
		onsubmit="return checkValue()">

		<table>
			<tr>
				<td id="title">ID</td>
				<td id="title">${member.id}</td>
			</tr>
			<tr>
				<td id="title">Password</td>
				<td><input type="password" name="password" maxlength="50"
					value="${member.password}"></td>
			</tr>
		</table>
		<br> <br>
		<table>

			<tr>
				<td id="title">Name</td>
				<td>${member.name}</td>
			</tr>

			<tr>
				<td id="title">Gender</td>
				<td>${member.gender}</td>
			</tr>

			<tr>
				<td id="title">Birth</td>
				<td>${member.birthyy}.${member.birthmm}.${member.birthdd}</td>
			</tr>

			<tr>
				<td id="title">E-Mail</td>
				<td><input type="text" name="mail1" maxlength="50"
					value="${member.mail1}"> @ <select name="mail2" id="mail2">
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
				</select></td>
			</tr>

			<tr>
				<td id="title">Phone</td>
				<td><input type="text" name="phone" value="${member.phone}" /></td>
			</tr>
			<tr>
				<td id="title">Address</td>
				<td><input type="text" size="50" name="address"
					value="${member.address}" /></td>
			</tr>
		</table>
		<br> <br> <input type="submit" value="Modify" /> <input
			type="button" value="Cancel"
			onclick="javascript:window.location='MainForm.do'">
	</form>

</body>
</html>