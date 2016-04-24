<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	.otherUsers{
		background: #dcdcdc;
		width: 100%;
	}
	.messages{
		background: #cdcdcd;
		width: 100%;
	}
</style>
</head>
<body>
	<%
		String userName = null;
		Cookie[] userSession = request.getCookies();
		if(userSession != null){
			for (Cookie cookie : userSession){
				if (cookie.getName().equals("user")){
					userName = cookie.getValue();
				}
			}
		}
		if(userName == null){
			response.sendRedirect("login.jsp");
		}
	%>
	<center><h1><%=userName %></h1></center>
	<div class="otherUsers">
		<p>${list}</p>
	</div>
	<div class="messages">
		<p>${messages}</p>
	</div>
</body>
</html>