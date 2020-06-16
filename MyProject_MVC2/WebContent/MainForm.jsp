<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String contentPage = request.getParameter("contentPage");
if (contentPage == null) {
	contentPage = "FirstView.jsp";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#wrap {
	width: 800px;
	margin: 0 auto 0 auto;
}

#header {
	text-align: center;
	width: 800px;
	height: 150px;
	background-color: tomato;
	padding: 60px 0px;
}

#main {
	float: left;
	width: 800px;
	height: 500px;
	background-color: wheat;
	text-align: center;
	vertical-align: middle;
}

#footer {
	clear: left;
	width: 800px;
	height: 60px;
	background-color: tomato;
}
</style>

<script type="text/javascript">
	function logoutPro() {
		location.href = "member/pro/SignOutPro.jsp";
	}
</script>
</head>
<body>

	<div id="wrap">
		<div id="header">
			<jsp:include page="Header.jsp" />
		</div>
		<div id="main">
			<jsp:include page="<%=contentPage%>" />
		</div>
		<div id="footer">
			<jsp:include page="Footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>