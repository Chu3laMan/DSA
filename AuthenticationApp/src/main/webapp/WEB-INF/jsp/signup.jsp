<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
        <h1>Add ID & Password</h1>
        <br />
        <form action="/app/signup"
            method="post">
 
            <table border="0" cellpadding="10">
                <tr>
                    <td>ID:</td>
                    <td><input type="email" name="email" required/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="pswd" required/></td>
                </tr>
                <!--  
                <tr>
                    <td>Phone:</td>
                    <td><input type="text" name="phone" required/></td>
                </tr>
                -->                     
                <tr>
                    <td colspan="2"><button type="submit">Save</button> </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>