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
<jsp:useBean id="memberBean" class="jsp.member.model.MemberBean" />
<jsp:setProperty property="*" name="memberBean"/>

<%
String id = (String)session.getAttribute("sessionID");
memberBean.setId(id);

MemberDAO dao = new MemberDAO();
dao.updateMember(memberBean);
%>
<br><br>
<font size="5" color="black">Modify Success</font>
<br><br>
<input type="button" value="Go to Main" onclick="javascript:window.location='MainForm.jsp'">

</body>
</html>