<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- EL로 변경하였음 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br> Today Visitors :
	${sessionScope.todayCount}
	<br> Total Visitors :
		${sessionScope.totalCount}
	<br>
</body>
</html>