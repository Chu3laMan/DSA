<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="firstEmail" method="post">
<input type="hidden" name="action" value="add">

<p><i>${message}</i></p>

<label>First Name:</label>
<input type="text" name="firstName" required><br>

<label>Last Name:</label>
<input type="text" name="lastName" required><br>

<label>Email:</label>
<input type="email" name="email" required><br>

<label>Password</label>
<input type="password" name="password" required><br>

<label>Confirm Password</label>
<input type="password" name="confirm_pswd" required><br>

<button>Submit</button>

</form>
</body>
</html>