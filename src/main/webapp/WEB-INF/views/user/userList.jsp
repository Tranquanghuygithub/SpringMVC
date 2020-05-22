<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Danh sach nguoi dung</h2>
	<hr/>
	<table>
	   <tr>
	      <th>ID</th>
	      <th>Name</th>
	      <th>Phone</th>
	      <th>Option</th>
	   </tr>
	 <c:forEach items="${users}" var="user">
	   <tr>
	      <td>${user.id}</td>
	      <td>${user.name}</td>
	      <td>${user.phone}</td>
	      <td> 
	      	<a href=" <c:url value="/detail-user/${user.id}"/> "> Xem chi tiet</a>
	      	<a href=" <c:url value="/delete-user/${user.id}"/> "> Xoa khach hàng</a>
	      	<a href=" <c:url value="/update-user/${user.id}"/> "> Sửa khách hàng</a>
	      </td>
	   </tr>
	 </c:forEach>
	
	</table>
	<a href=" <c:url value="/add-user"/> "> Thêm khách hàng</a>
</body>
</html>