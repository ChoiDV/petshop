<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link href="${conPath }/css/style.css" rel="stylesheet">
 <style>
 
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 		answer = confirm('정말 삭제하시겠습니까?');
 	 	if(answer == true){
 	 		location.href="${conPath }/DogDeleteReply.do?rno=${rno }&dnum=${dnum }";
 	 	} else {
 	 		alert('삭제 취소되었습니다.');
 	 		history.back();
 	 	}
 </script>  
</head>
  <body>
	
  </body>
</html>