<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><i>${message}</i></p>
<h1>Thanks!</h1>
<label>First Name :</label>
<input type="text" value="${user.firstName}"><br>

<label>Last Name :</label>
<span>${user.lastName}</span><br>

<label>Email :</label>
<span>${user.email}</span><br>

<label>Password :</label>
<span>${user.password}</span>

</body>
</html>