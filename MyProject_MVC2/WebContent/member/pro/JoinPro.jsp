<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");

	String[] gender = request.getParameterValues("gender");

	String[] birthmm = request.getParameterValues("birthmm");
	String birthdd = request.getParameter("birthdd");
	String birthyy = request.getParameter("birthyy");

	String email1 = request.getParameter("email1");
	String[] email2 = request.getParameterValues("email2");

	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	%>

	<div id="wrap">
		<br> <br> <b> <font size="6" color="darkgrey">Welcome!</font>
		</b> <br> <br> <br>
	</div>

	<table>
		<tr>
			<td id=title>ID</td>
			<td><%=id%></td>
		</tr>
		<tr>
			<td id=title>Password</td>
			<td><%=password%></td>
		</tr>
		<tr>
			<td id=title>Name</td>
			<td><%=name%></td>
		</tr>
		<tr>
			<td id=title>Gender</td>
			<td>
				<%
					for (String g : gender) {
					out.println(g);
				}
				%>
			</td>
		</tr>
		<tr>
			<td id=title>Birth</td>
			<td><%=birthdd%> . <%
				for (String mm : birthmm) {
				out.println(mm);
			}
			%> . <%=birthyy%></td>
		</tr>
		<tr>
			<td id=title>E-Mail</td>
			<td><%=email1%><%
 	for (String e : email2) {
 	out.println("@" + e);
 }
 %></td>
		</tr>
		<tr>
			<td id=title>Phone</td>
			<td><%=phone%></td>
		</tr>
		<tr>
			<td id=title>Address</td>
			<td><%=address%></td>
		</tr>
	</table>
	<br>
	<div id="wrap">
		<input type="button" value="Confilm">
	</div>
</body>
</html>