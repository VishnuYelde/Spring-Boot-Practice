<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="student" method="post">
		Name : <input type="text" name="name" placeholder="Enter Name" required><br>
		Email : <input type="email" name="email" placeholder="Enter Email" required><br>
		Age : <input type="number" name="age" placeholder="Enter Age" required><br>
		Standard : <input type="text" name="std" placeholder="Enter Standard" required><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>