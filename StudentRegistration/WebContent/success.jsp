<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.softra.model.Student"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%Student student = (Student)request.getAttribute("student"); %>
	
	<h2>Registered Successfully!</h2>
	<p>Name: ${student.getName()}</p>
	<p>Age: ${student.getAge()}</p>
	<p>Mobile: ${student.getMobile()}</p>
	<p>Address: ${student.getAddress()}</p>

</body>
</html>