<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
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

	function checkValue() {
		var form = document.forms[0];

		var comment_num = "${comment.comment_num}";
		var comment_content = form.comment_content.value

		if (!comment_content) {
			alert("Input Content");
			return false;
		} else {
			var param = "comment_num=" + comment_num + "&comment_content="
					+ comment_content;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "CommentUpdateAction.co", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	}

	function checkFunc() {
		if (httpRequest.readyState == 4) {
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				if (opener != null) {
					window.opener.document.location.reload();
					opener.updateForm = null;
					self.close();
				}
			}
		}
	}
</script>
</head>
<body>
	<div id="wrap">
		<br> <b><font size="5" color="darkgrey">Comment Reply</font></b>
		<hr>
		<br>
		<div id="commentReplyForm">
			<form name="replyInfo" target="parentForm">
				<textarea rows="7" cols="70" name="comment_content">${comment.comment_content}</textarea>
				<br> <br> <input type="button" value="Apply"
					onclick="checkValue()"> <input type="button" value="Cancel"
					onclick="window.close()">
			</form>
		</div>
	</div>
</body>
</html>