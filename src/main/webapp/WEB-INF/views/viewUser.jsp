<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/rescource/js/main.js"/>"></script>
</head>
<body>
	<p>Thong tin nguoi dung</p>
	<p>Ten: ${u.name}</p>
	<p>Mat Khau: ${u.password}</p>
	<p>Id: ${u.id}</p>
	<p>Giới tính: ${u.gender}</p>
	<p>Thông tin: ${u.about}</p>
	<p> Đồng ý với điều khoản: ${u.acceptAgreement}</p>
	 <p> Sở thích: </p>
	 <c:forEach items="${u.favourites}" var="item">
	    <p> ${item}</p>
	 </c:forEach>
	 <p>Ten file: ${u.avatar.getOriginalFilename()} </p>
</body>
</html>