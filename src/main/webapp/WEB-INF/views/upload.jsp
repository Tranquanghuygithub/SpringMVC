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
       <p>Form Uploas </p>
       <c:url value="/upload-file" var="url"/>
       <form method="POST" action="${url}" enctype="multipart/form-data">
          <input type="file" name="file">
          <input type="file" name="file">

           <button type="submit">Submit</button>  
       </form>
</body>
</html>