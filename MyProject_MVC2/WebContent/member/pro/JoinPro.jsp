<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jsp.member.model.MemberBean"%>
<%@ page import="jsp.member.model.MemberDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>


	<jsp:useBean id="memberBean" class="jsp.member.model.MemberBean"></jsp:useBean>
	<jsp:setProperty property="*" name="memberBean" />

	<%
	MemberDAO dao = MemberDAO.getInstance();
	System.out.println("After get dao:"+dao);
	dao.insertMember(memberBean);
	%>


	<div id="wrap">
		<br> <br> <b> <font size="6" color="darkgrey">Welcome!</font>
		</b> <br> <br> <br>
	</div>

	<table>
		<tr>
			<td id=title>ID</td>
			<td><%=memberBean.getId()%></td>
		</tr>
		<tr>
			<td id=title>Password</td>
			<td><%=memberBean.getPassword()%></td>
		</tr>
		<tr>
			<td id=title>Name</td>
			<td><%=memberBean.getName()%></td>
		</tr>
		<tr>
			<td id=title>Gender</td>
			<td><%=memberBean.getGender()%></td>
		</tr>
		<tr>
			<td id=title>Birth</td>
			<td><%=memberBean.getBirthdd()%>. <%=memberBean.getBirthmm()%>.
				<%=memberBean.getBirthyy()%></td>
		</tr>
		<tr>
			<td id=title>E-Mail</td>
			<td><%=memberBean.getMail1()%>@<%=memberBean.getMail2()%></td>
		</tr>
		<tr>
			<td id=title>Phone</td>
			<td><%=memberBean.getPhone()%></td>
		</tr>
		<tr>
			<td id=title>Address</td>
			<td><%=memberBean.getAddress()%></td>
		</tr>
	</table>
	<br>
	<div id="wrap">
		<input type="button" value="Confilm">
	</div>
</body>
</html>