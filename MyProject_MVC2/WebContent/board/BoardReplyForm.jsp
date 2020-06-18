<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#title {
	height: 16;
	font-size: 12;
	font-weight: bold;
	text-align: center;
}

#comentBoard {
	width: 700px;
	border: 1px dotted tomato;
	text-align: center;
}
</style>
</head>
<body>
	<br>
	<b><font size="6" color="darkgrey">Comment Page</font></b>
	<br>
	<form method="POST" action="BoardReplyAction.bo?page=${page}" name="boardForm">

		<!-- 부모글의 정보, 페이지번호 전달 -->
		<input type="hidden" name="board_id" value="${sessionScope.sessionID}">
		<input type="hidden" name="board_num" value="${board.board_num}"/>
		<input type="hidden" name="board_re_ref" value="${board.board_re_ref}"/>
		<input type="hidden" name="board_re_lev" value="${board.board_re_lev}"/>
		<input type="hidden" name="board_re_seq" value="${board.board_re_seq}"/>

		<table id="comentBoard">
			<tr>
				<td id="title">Commenter</td>
				<td>${sessionScope.sessionID}</td>
			</tr>
			<tr>
				<td id="title">Title</td>
				<td><input type="text" name="board_subject" size="70"
					maxlength="100" value="" /></td>
			</tr>
			<tr>
				<td id="title">Comment</td>
				<td><textarea name="board_content" rows="15" cols="72"></textarea>
				</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5"><input type="reset" value="Cancel"> <input
					type="Submit" value="Submit"> <input type="button"
					value="Back" onclick="javascript:history.go(-1)"></td>
			</tr>
		</table>
	</form>
</body>
</html>