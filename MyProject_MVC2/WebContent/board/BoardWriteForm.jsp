<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#title {
	height: 16;
	font-size: 12;
	text-align: center;
	text-weight: bold;
}

#writeForm {
	width: 700px;
	border: 1px dotted tomato;
	text-align: center;
}
</style>
</head>
<body>
	<br>
	<b><font size="6" color="darkgrey">Write</font></b>
	<br>
	<form method="POST" action="BoardWriteAction.bo" name="boardForm"
		enctype="multipart/form-data">
		<input name="board_id" type="hidden" value="${sessionScope.sessionID}" />
		<table id="writeForm">
			<tr>
				<td id="title">Writer</td>
				<td>${sessionScope.sessionID}</td>
			</tr>
			<tr>
				<td id="title">Title</td>
				<td><input name="board_subject" type="text" size="70"
					maxlength="100" value="" /></td>
			</tr>
			<tr>
				<td id="title">Content</td>
				<td><textarea name="board_content" rows="15" cols="72"></textarea>
				</td>
			</tr>
			<tr>
				<td id="title">File</td>
				<td><input name="board_file" type="file" /></td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5"><input type="reset" value="Cancel"> <input
					type="submit" value="Write"> <input type="button"
					value="List"></td>
			</tr>

		</table>
	</form>
</body>
</html>