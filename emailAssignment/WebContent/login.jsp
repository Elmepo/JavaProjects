<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	.errorColor{
		color: red;
	}
</style>
</head>
<body>
	<center>
		<h1>Please log in to continue</h1>
		<%
			String errors = " ";
			Cookie[] userSession = request.getCookies();
			if(userSession != null){
				for(Cookie cookie : userSession){
					if(cookie.getName().equals("errors")){
						errors = cookie.getValue();
					}
				}
			}
		%>
		<p class="errorColor"><%=errors %></p>
		<form action="LoginServlet" method="post">
			<input type="text" name="uname"><br>
			<input type="password" name="pass">
			<br>
			<button type="submit">Submit</button>
		</form>
		<a href="register.jsp">Register</a>
	</center>
</body>
</html>