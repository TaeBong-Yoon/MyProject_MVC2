<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- https://sjh836.tistory.com/136 - jstl 사용법 참고 -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#wrap {
	width: 99%;
	margin: 0 auto 0 auto;
}

#header {
	text-align: center;
	width: 99%;
	height: 150px;
	background-color: darkgrey;
	padding: 60px 0px;
}

#main {
	float: left;
	width: 99%;
	height: 500px;
	background-color: white;
	text-align: center;
	vertical-align: middle;
	overflow: auto;
}

#footer {
	clear: left;
	width: 99%;
	height: 60px;
	background-color: darkgrey;
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
			<c:set var="contentPage" value="${param.contentPage}"/>
			<c:if test="${contentPage==null }">
				<jsp:include page="FirstView.jsp" />
			</c:if>
			<jsp:include page="${contentPage}" />
		</div>
		<div id="footer">
			<jsp:include page="Footer.jsp" />
		</div>
	</div>

</body>
</html>