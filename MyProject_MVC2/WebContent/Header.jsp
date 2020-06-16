<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">

<style type="text/css">
#wrap {
	text-align: center;
	width: 800px;
	height: 150px;
}
</style>
<script type="text/javascript">
	function changeView(value) {
		if (value == "0") {
			location.href = "MainForm.do";
		} else if (value == "1") {
			location.href = "LoginForm.do";
		} else if (value == "2") {
			location.href = "JoinForm.do";
		} else if (value == "3") {
			location.href = "MemberLogoutAction.do";
		} else if (value == "4") {
			location.href = "MemberInfoAction.do";
		} else if (value == "5") {
			location.href = "MemberListAction.do";
		}
	}
</script>
</head>
<body>
	<div id="wrap">
		<p>
			<button class="btn btn-success" onclick="changeView(0)">HOME</button>
			<!-- 로그인 안되었을 경우 - sign in, sign up 버튼 -->
			<c:if test="${sessionScope.sessionID==null}">
				<button id="loginBtn" class="btn btn-primary"
					onclick="changeView(1)">Sign In</button>
				<button id="joinBtn" class="btn btn-primary" onclick="changeView(2)">Sign
					Up</button>
			</c:if>
			<!-- 로그인 되었을 경우 - sign out, my info 버튼 -->
			<c:if test="${sessionScope.sessionID!=null}">
				<button id="logoutBtn" class="btn btn-primary"
					onclick="changeView(3)">Sign Out</button>
				<button id="updateBtn" class="btn btn-success"
					onclick="changeView(4)">My Info</button>
			<!-- 로그인 된 경우 - amdmin 로그인 시 유저 정보 버튼 -->
				<c:if test="${sessionScope.sessionID=='admin'}">
					<button id="memberViewBtn" class="btn btn-warning"
						onclick="changeView(5)">See All Users</button>
				</c:if>
			</c:if>
		</p>
	</div>
</body>
</html>