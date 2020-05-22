<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>Thêm khach hang</h1>
		<c:url value="/add-user" var="url"/>
		<form:form  modelAttribute="user" method="POST"  action="${url}"  enctype="multipart/form-data">
		   <form:input path="name"/>
		   <form:input path="phone"/>
		   <br/>
		   <form:button type="submit"> Cập nhập thông tin</form:button>
		 </form:form>

</body>
</html>