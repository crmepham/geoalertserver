<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Test user authentication</p>
<form id="loginForm" action="http://localhost:8080/geoalertserver/api/v1/user/authenticate" method="post">
<input type="text" id="username" name="username" placeholder="username">
<input type="text" id="password" name="password" placeholder="password">
<input type="submit" name="loginSubmit" id="loginSubmit" value="Login">
</form>
<hr>
</body>
</html>