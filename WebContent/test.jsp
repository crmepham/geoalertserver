<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!HTML>
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
<input type="submit" value="Confirm email">
</form>
<hr>
<p>Retrieve security question</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/security/question" method="post">
<input type="text" name="email" placeholder="email">
<input type="submit" value="Retrieve security question">
</form>
<hr>
<p>Retrieve user information</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/profile/information" method="post">
<input type="text" name="username" placeholder="username">
<input type="submit"value="Retrieve user information">
</form>
<hr>
<p>Update user information</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/update/profile/information" method="post">
<i>ccc,ccc,1111-11-11 11:11:11,111,111,11,111,111,11,111,111,1111</i><br>
<input type="text" name="username" placeholder="username">
<input type="text" name="profileInfo" placeholder="comma seperated user data">
<input type="submit" value="Update user information">
</form>
<hr>
<p>Update user status</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/update/status" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="status" placeholder="status">
<input type="submit" value="Retreive user location">
</form>
<hr>
<p>Upload profile image</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/upload/profile/image" method="post" enctype="multipart/form-data">
<input type="text" name="username" placeholder="username">
<input type="file" name="image">
<input type="submit" value="upload">
</form>
<hr>
<p>Get profile image bytes</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/profile/image" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" value="upload">
</form>
<hr>
<p>Get profile status</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retreive/status" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" value="Get status">
</form>
<hr>
<p>Retrieve user contacts</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/user/contacts" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" value="Retrieve contacts">
</form>
<hr>
<p>Delete user contact</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/delete/contact" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="contactId" placeholder="user ID">
<input type="submit" value="Delete contact">
</form>
<hr>
<p>Gets user pending contact requests</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retrieve/pending/contact/requests" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" value="Retrieve contact requests">
</form>
<hr>
<p>Accept user contact request</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/accept/contact/request" method="post" >
<input type="text" name="userId" placeholder="user ID">
<input type="text" name="username" placeholder="username">
<input type="submit" value="Accept user contact request">
</form>
<hr>
<p>Accept user contact request</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/decline/contact/request" method="post" >
<input type="text" name="userId" placeholder="user ID">
<input type="text" name="username" placeholder="username">
<input type="submit" value="Decline user contact request">
</form>
<hr>
<p>Add contact</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/add/contact" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="contactUsername" placeholder="contacts username">
<input type="submit" value="Add Contact">
</form>
<hr>
<p>Add/update user location</p>
<form action="http://localhost:8080/geoalertserver/api/v1/location/update" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="latitude" placeholder="latitude">
<input type="text" name="longitude" placeholder="longitude">
<input type="submit" value="Add/update location">
</form>
<hr>
<p>Retreive user location</p>
<form action="http://localhost:8080/geoalertserver/api/v1/location/retreive" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" value="Retreive user location">
</form>
<hr>
<p>Add user notification</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/add/notification" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="contactUsername" placeholder="contact username">
<input type="submit" value="Add user notification">
</form>
<hr>
<p>Retrieve user notifications</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/retreive/notifications" method="post" >
<input type="text" name="username" placeholder="username">
<input type="submit" value="Retreive user notifications">
</form>
<hr>
<p>Delete user notification</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/delete/notification" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="contactUsername" placeholder="contact username">
<input type="submit" value="Delete user notification">
</form>
<hr>
<p>Toggle map view</p>
<form action="http://localhost:8080/geoalertserver/api/v1/user/update/map/view" method="post" >
<input type="text" name="username" placeholder="username">
<input type="text" name="showMap" placeholder="true or false">
<input type="submit" value="Toggle map view">
</form>
<hr>
</body>
</html>