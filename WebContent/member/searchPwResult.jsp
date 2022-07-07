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
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
	<c:if test="${not empty searchPwResult }">
		<script>
			alert('${searchPwResult.mid }님의 비밀번호는 : ${searchPwResult.mpw }');
			location.href="${conPath }/loginView.do?mid=${searchPwResult.mid }";
		</script>
	</c:if>
	<c:if test="${empty searchPwResult }">
		<script>
			alert('정보를 잘못 입력하셨습니다.');
			history.back();
		</script>
	</c:if>
  </body>
</html>