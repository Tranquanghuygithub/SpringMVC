<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>Trang Chi Tiet Nguoi Dung </h1>
	   <hr/>
	      ID: 
	      <p>${user.id}</p>
	      Ten:
	      <p>${user.name}</p>
	      SDT:
	      <p>${user.phone}</p>
</body>
</html>