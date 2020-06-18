<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#wrap {
	width: 800px;
	margin: 0 auto 0 auto;
}

#derailBoard {
	text-align: center;
}

#title {
	height: 16;
	font-size: 12;
	text-align: center;
	text-weight: bold;
}

#detailBoard {
	width: 800px;
	border: 1px dotted tomato;
}
</style>
</head>
<body>
	<div id="wrap">
		<br> <br>
		<div id="board">
			<table id="detailBoard">
				<tr>
					<td id="title">Date</td>
					<td>${board.board_date}</td>
				</tr>
				<tr>
					<td id="title">Writer</td>
					<td>${board.board_id}</td>
				</tr>
				<tr>
					<td id="title">Title</td>
					<td>${board.board_subject}</td>
				</tr>
				<tr>
					<td>Content</td>
					<td>${board.board_content}</td>
				</tr>
				<tr>
					<td id="title">File</td>
					<td><a
						href="FileDownloadAction.bo?file_name=${board.board_file}">${board.board_file}</a>
					</td>
				</tr>

				<tr align="center" valign="middle">
					<td colspan="5"><input type="button" value="Modify"> <input
						type="button" value="Delete"> <input type="button"
						value="Coment"> <input type="button" value="List"
						onclick="javascript:location.href='BoardListAction.bo?page=${pageNum}'">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>