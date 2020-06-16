<%@page import="jsp.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");
String password = request.getParameter("password");

MemberDAO dao = new MemberDAO();
int check = dao.loginCheck(id, password);

String message = "";

if(check == 1) {
	session.setAttribute("sessionID", id);
	message = "../../MainForm.jsp";
} else if(check == 0){
	message = "../../MainForm.jsp?contentPage=member/view/LoginForm.jsp?msg=0";
} else if(check == -1){
	message = "../../MainForm.jsp?contentPage=member/view/LoginForm.jsp?msg=-1";
}
response.sendRedirect(message);

%>
</body>
</html>