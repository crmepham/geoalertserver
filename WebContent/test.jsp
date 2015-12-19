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
<form action="http://localhost:8080/geoalertserver/api/v1/user/authenticate" method="post">
<input type="text" name="username" placeholder="username">
<input type="text" name="password" placeholder="password">
<input type="submit" name="loginSubmit" value="Login">
</form>
<hr>
<p>Test user registration</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/register" method="post">
<input type="text" name="username" placeholder="username">
<input type="text" name="password" placeholder="password">
<input type="text" name="email" placeholder="email">
<input type="text" name="contactNumber" placeholder="phone number">
<select name="lang">
	<option value="en">en</option>
</select>
<input type="submit" name="registerSubmit" value="Register">
</form>
<hr>
<p>Test account recovery</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/account/recover" method="post">
<input type="text" name="email" placeholder="email">
<input type="submit" name="accountRecoverSubmit" value="Send email">
</form>
<hr>


</body>
</html>