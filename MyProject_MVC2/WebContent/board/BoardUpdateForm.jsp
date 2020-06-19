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
	font_weight: bold;
	text-align: center;
}

#writeForm {
	width: 700px;
	border: 1px dotted tomato;
	text-align: center;
}
</style>
<script type="text/javascript">
	function changeView() {
		location.href = 'BoardListAction.bo?page=${pageNum}';
	}
</script>
</head>
<body>
	<br>
	<b><font size="6" color="darkgrey">Update</font></b>
	<br>

	<form method="POST" action="BoardUpdateAction.bo?page=${pageNum}"
		name="boardForm" enctype="multipart/form-data">

		<input type="hidden" name="board_num" value="${board.board_num}">
		<input type="hidden" name="board_file" value="${board.board_file}">

		<table id="writeForm">
			<tr>
				<td id="title">Writer</td>
				<td>${board.board_id}</td>
			</tr>
			<tr>
				<td id="title">Title</td>
				<td><input type="text" name="board_subject" size="70"
					maxlength="100" value="${board.board_subject}"></td>
			</tr>
			<tr>
				<td id="title">Content</td>
				<td><textarea rows="15" cols="72" name="board_content">${board.board_content }</textarea>
				</td>
			</tr>
			<tr>
				<td id="title">File</td>
				<td>${board.board_file}</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5"><input type="reset" value="Cancel"> <input
					type="submit" value="Submit"> <input type="button"
					value="List" onclick="changeView()"></td>
			</tr>
		</table>
	</form>
</body>
</html>