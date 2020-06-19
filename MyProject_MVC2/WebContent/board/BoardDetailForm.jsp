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
<script type="text/javascript">
	function changeView(value) {
		if (value == 0) {
			location.href = "BoardListAction.bo?page=${pageNum}";
		} else if (value == 1) {
			location.href = 'BoardReplyFormAction.bo?num=${board.board_num}&page=${pageNum}';
		}
	}
	
	function doAction(value){
		if(value == 0){
			location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
		} else if(value == 1){
			location.href="BoardDeleteAction.bo?num=${board.board_num}";
		}
	}
</script>
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
					<td colspan="5"><c:if test="${sessionScope.sessionID !=null}">
							<c:if test="${sessionScope.sessionID == board.board_id}">
								<input type="button" value="Modify" onclick="doAction(0)">
								<input type="button" value="Delete" onclick="doAction(1)">
							</c:if>
							<input type="button" value="Comment" onclick="changeView(1)">
						</c:if> <input type="button" value="List" onclick="changeView(0)">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>