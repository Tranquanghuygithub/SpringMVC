<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/rescource/js/main.js"/>"></script>
</head>
<body>
       <p>Form nguoi dung </p>
       <c:url value="/them-user" var="url"/>
       <form:form modelAttribute="user" method="POST" action="${url}" enctype="multipart/form-data" >
           Ten: <form:input path="name"/>
           <!-- Ten thuoc tinh user= name -->
           <p style="color:red"><form:errors path="name"></form:errors></p>
          <p> Mạt Khau:</p> <form:password path="password"/>
          <p style="color:red"><form:errors path="password"></form:errors></p>
           <form:hidden path="id"/>
           <!-- value la cai doi tuong thuc te trong doi tuong, label gia trị hien thi tren html -->
          <p> Sở thích: </p> <form:checkbox path="favourites" value="Xem phim" label="xemp phim"/>
           			 <form:checkbox path="favourites" value="Nghe nhac" label="Nghe nhac"/>
           			 <form:checkbox path="favourites" value="code" label="Coding"/>
          <p> Giới tính: </p>
                     <form:select path="gender">
                         <form:option value="Nam">Nam</form:option>
                         <form:option value="Nữ"> Nữ</form:option>
                     </form:select>
           <p>Giới thiệu:</p>
                     <form:textarea path="about"/><br>
                     <form:radiobutton path="acceptAgreement" value="true" label="Dong y voi dieu khoan"/>
           
           <form:input path="avatar" type="file" />
           
           <button type="submit">Submit</button>   
       </form:form>
</body>
</html>