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
<p>Confirm email exists</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/confirm/email" method="post">
<input type="text" name="email" placeholder="email">
<input type="submit" name="confirmEmailSubmit" value="Confirm email">
</form>
<hr>
<p>Retrieve security question</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/security/question" method="post">
<input type="text" name="email" placeholder="email">
<input type="submit" name="confirmEmailSubmit" value="Retrieve security question">
</form>
<hr>
<p>Retrieve user information</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/profile/information" method="post">
<input type="text" name="username" placeholder="username">
<input type="submit" name="confirmEmailSubmit" value="Retrieve user information">
</form>
<hr>
<p>Upload profile image</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/upload/profile/image" method="post" enctype="multipart/form-data">
<input type="text" name="username" placeholder="username">
<input type="file" name="image">
<input type="submit" name="confirmEmailSubmit" value="upload">
</form>
<hr>
<p>Get profile image bytes</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/profile/image" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" name="confirmEmailSubmit" value="upload">
</form>
<hr>
</body>
</html>