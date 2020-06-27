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
	width: 99%;
	margin: 0 auto 0 auto;
}

#topForm {
	text-align: right;
}

#board, #pageForm, #serchForm {
	text-align: center;
	padding : 0 20%;
}

#bList {
	text-align: center;
	width: 700px;
}
</style>
<script type="text/javascript">
	function writerForm() {
		location.href = "BoardWriteForm.bo";
	}
</script>
</head>
<body>
	<div id="wrap">
		<!-- 목록의 윗 부분 -->
		<br>
		<div id="topForm">
			<c:if test="${sessionScope.sessionID!=null}">
				<input type="button" value="Write" onclick="writerForm()">
			</c:if>
		</div>
		<br>
		<!-- 게시글의 목록 부분 -->
		<div id="board">
			<table id="bList">
				<tr height="30">
					<td>Num</td>
					<td>Title</td>
					<td>Writer</td>
					<td>Date</td>
					<td>View</td>
				</tr>
				<c:forEach var="board" items="${requestScope.list}">
					<tr>
						<td>${board.board_num}</td>
						<td align="left"><a
							href="BoardDetailAction.bo?num=${board.board_num}&pageNum=${spage}">
								${board.board_subject} </a></td>
						<td><a href="#">${board.board_id}</a></td>
						<td>${board.board_date}</td>
						<td>${board.board_count}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<!-- 페이지 넘버링 부분 -->
		<div id="pageForm">
			<c:if test="${startPage != 1}">
				<a href='BoardListAction.bo?page=${startPage-1}'>[ Previous ]</a>
			</c:if>

			<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
				<c:if test="${pageNum == spage}">
		${pageNum}&nbsp;
		</c:if>
				<c:if test="${pageNum != spage}">
					<a href="BoardListAction.bo?page=${pageNum}">${pageNum}&nbsp;</a>
				</c:if>
			</c:forEach>

			<c:if test="${endPage != maxPage}">
				<a href='BoardListAction.bo?page=${endPage+1}'>[ Next ]</a>
			</c:if>

		</div>
		<br>
		<div id="serchForm">
			<form>
				<select name="opt">
					<option value="0">Title</option>
					<option value="1">Content</option>
					<option value="2">Title+Content</option>
					<option value="3">Writer</option>
				</select> <input type="text" size="20" name="condition" />&nbsp; <input
					type="submit" value="Search" />
			</form>
		</div>
	</div>
</body>
</html>