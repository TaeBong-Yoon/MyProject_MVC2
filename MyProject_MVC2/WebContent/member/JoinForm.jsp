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
		if (!document.userInfo.id.value) {
			alert("Insert ID.");
			return false;
		}
		if (form.idDuplication.value != "idCheck") {
			alert("Check Duplicate ID");
			return false;
		}

		if (!document.userInfo.password.value) {
			alert("Insert PassWord.");
			return false;
		}

		if (document.userInfo.password.value != document.userInfo.passwordcheck.value) {
			alert("PassWord not same.");
			return false;
		}

		if (!form.name.value) {
			alert("Input Name");
			return false;
		}
		if (!form.birthdd.value || !form.birthmm.value == "00"
				|| !form.birthyy.value) {
			alert("Input Birthday");
			return false;
		}
		if (isNaN(form.birthdd.value) == false) {
			alert("You have to input number in Birthday");
			return false;
		}
		if (isNaN(form.birthyy.value) == false) {
			alert("You have to input number in Birthday");
			return false;
		}
		if (!form.mail1.value) {
			alert("Input E-Mail");
			return false;
		}
		if (!form.phone.value) {
			alert("Input Phone number");
			return false;
		}
		if (isNaN(form.phone.value) == false) {
			alert("You have to input number in Phone");
			return false;
		}
		if (!form.address.value) {
			alert("Input Address");
			return false;
		}
	}

	function passCheck() {
		if (document.userInfo.password.value == document.userInfo.passwordcheck.value) {
			document.getElementById('sametext').innerHTML = "P/W Same.";
		} else {
			document.getElementById('sametext').innerHTML = "P/W Different.";
		}
	}

	function goFirstForm() {
		location.href = "MainForm.do";
	}

	function openIdChk() {
		window.name = "parentForm";
		window.open("member/IdCheckForm.jsp", "chkForm",
				"width=500, height=300, resizable = no, scrollbars= no");
	}
	function inputIdChk() {
		document.userInfo.idDuplication.value = "idUncheck";
	}
</script>

</head>
<body>
	<br>
	<br>
	<b> <font size="6" color="darkgrey">Sign Up</font>
	</b>
	<br>
	<br>
	<br>
	<form method="post" action="MemberJoinAction.do" name="userInfo"
		onsubmit="return checkValue()">
		<table>
			<tr>
				<td id="title">ID</td>
				<td><input type="text" name="id" maxlength="20" onkeydown="inputIdChk()" readonly> <input
					type="button" value="ID Check" onclick="openIdChk()">
				<input type="hidden" name="idDuplication" value="idUncheck">
				</td>
			</tr>

			<tr>
				<td id="title">PassWord</td>
				<td><input type="password" name="password" maxlength="15"
					onchange="passCheck()"></td>
			</tr>

			<tr>
				<td id="title">PassWord Check</td>
				<td><input type="password" name="passwordcheck" maxlength="15"
					onchange="passCheck()"></td>
					<td> <span id="sametext"></span></td>
			</tr>

			<tr>
				<td id="title">Name</td>
				<td><input type="text" name="name" maxlength="40"></td>
			</tr>

			<tr>
				<td id="title">Gender</td>
				<td><input type="radio" name="gender" value="M" checked>M
					<input type="radio" name="gender" value="F" checked>F</td>
			</tr>

			<tr>
				<td id="title">Birth</td>
				<td><input type="text" name="birthdd" maxlength="2"
					placeholder="dd" size="4"> <select name="birthmm">
						<option value="">Month</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
				</select> <input type="text" name="birthyy" maxlength="4" placeholder="yyyy"
					size="4"></td>
			</tr>

			<tr>
				<td id="title">E-Mail</td>
				<td><input type="text" name="mail1" maxlength="30">@ <select
					name="mail2">
						<option>naver.com</option>
						<option>gmail.com</option>
						<option>yahoo.com</option>
						<option>nate.com</option>
				</select></td>
			</tr>

			<tr>
				<td id="title">Phone</td>
				<td><input type="text" name="phone"></td>
			</tr>

			<tr>
				<td id="title">Address</td>
				<td><input type="text" size="50" name="address"></td>
			</tr>

		</table>
		<br> <input type="submit" value="Complete"> <input
			type="button" value="Cancel" onclick="goFirstForm()">
	</form>


</body>
</html>