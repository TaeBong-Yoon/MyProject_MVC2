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

#detailBoard {
	text-align: center;
	width: 800px;
	border: 1px dotted tomato;
}

#title {
	height: 16;
	font-size: 12;
	text-align: center;
	text-weight: bold;
}

#commentBoard {
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

	function doAction(value) {
		if (value == 0) {
			//수정
			location.href = "BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
		} else if (value == 1) {
			//삭제
			location.href = "BoardDeleteAction.bo?num=${board.board_num}";
		}
	}

	var httpRequest = null;

	/* httpRequest 객체 생성 https://jamong-icetea.tistory.com/150 */
	function getXMLHttpRequest() {

		var httpRequest = null;
		/* IE일 경우 */
		if (window.ActiveXObject) {
			try {
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e2) {
					httpRequest = null;
				}
			}
			/* 그 외 익스플로러일 경우 */
		} else if (window.XMLHttpRequest) {
			httpRequest = new window.XMLHttpRequest();
		}
		return httpRequest;
	}

	function writeCmt()
    {
        var form = document.getElementById("writeCommentForm");
        
        var board = form.comment_board.value
        var id = form.comment_id.value
        var content = form.comment_content.value;
        
        if(!content)
        {
            alert("Input Comment");
            return false;
        }
        else
        {    
            var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
                
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = checkFunc;
            httpRequest.open("POST", "CommentWriteAction.co", true);    
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=EUC-KR'); 
            httpRequest.send(param);
        }
    }


	function checkFunc() {
		if (httpRequest.readyState == 4) {
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				document.location.reload(); // Detail 창 새로고침
			}
		}
	}
	
	function cmReplyOpen(comment_num){
		
		var userId = '${sessionScope.sessionID}';
		
		if(userId == "" || userId == null){
			alert("Please Sign In first");
			return false;
		} else {
			window.name = "parentForm";
			window.open("CommentReplyFormAction.co?num="+comment_num,
					"replyForm", "width=570, height=350, resizable = no, scrollbars = no");
		}
		
	}
	
	function cmDeleteOpen(comment_num){
		
		var msg = confirm("Are you sure?");
		if(msg == true){ // 확인 누를경우
			deleteCmt(comment_num);
		} else {
			return false;
		}
		
	}
	
	function deleteCmt(comment_num){
		
		var param = "comment_num="+comment_num;
		
		httpRequest = getXMLHttpRequest();
		httpRequest.onreadystatechange = checkFunc;
		httpRequest.open("POST", "CommentDeleteAction.co", true);
		httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=UTF-8');
		httpRequest.send(param);
		
	}
	
	function cmUpdateOpen(comment_num){
		window.name = "parentForm";
		window.open("CommentUpdateFormAction.co?num="+comment_num,
				"updateForm","width=570, height=350, resizable=no,scrollbars=no");
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
						</c:if> <input type="button" value="List" onclick="changeView(0)">
					</td>
				</tr>
			</table>
		</div>
		<br> <br>
		<div id="comment">
			<table id="commentBoard">
				<c:if test="${requestScope.commentList != null}">
					<c:forEach var="comment" items="${requestScope.commentList}">
						<tr>
							<td width="150">
								<div>
									<c:if test="${comment.comment_parent > 0}">
										<b>Re</b>
									</c:if>
									${comment.comment_id} <br> <font size="2" color="darkgrey">${comment.comment_date}</font>
								</div>
							</td>
							<td width="550">
								<div class="text_wrapper">${comment.comment_content}</div>
							</td>
							<td width="100">
								<div id="btn" style="text-align: center;">
									<a href="#" onclick="cmReplyOpen(${comment.comment_num})">[Comment]</a>
									<c:if test="${comment.comment_id == sessionScope.sessionID}">
										<!-- 작성자만 수정,삭제 가능 -->
										<a href="#" onclick="cmUpdateOpen(${comment.comment_num})">[Modify]</a>
										<a href="#" onclick="cmDeleteOpen(${comment.comment_num})">[Delete]</a>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<!-- 로그인 시에만 댓글 작성 가능 -->
				<c:if test="${sessionScope.sessionID !=null}">
					<form id="writeCommentForm">
						<input type="hidden" name="comment_board"
							value="${board.board_num}"> <input type="hidden"
							name="comment_id" value="${sessionScope.sessionID}">
						<!-- 아이디-->
						<td width="150">
							<div>${sessionScope.sessionID}</div>
						</td>
						<!-- 본문 작성-->
						<td width="550">
							<div>
								<textarea name="comment_content" rows="4" cols="70"></textarea>
							</div>
						</td>
						<!-- 댓글 등록 버튼 -->
						<td width="100">
							<div id="btn" style="text-align: center;">
								<p>
									<a href="#" onclick="writeCmt()">[Comment]</a>
								</p>
							</div>
						</td>
					</form>
				</c:if>
			</table>
		</div>
	</div>
</body>
</html>