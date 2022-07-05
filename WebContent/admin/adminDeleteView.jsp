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
  <jsp:include page="../main/header.jsp"/>
  	
		<table>
			<c:forEach var="admin" items="${adminList }">
				<tr>
					<td>관리자 이름 : ${admin.aname }</td>
					<td>관리자 아이디 : ${admin.aid }</td>
					<td>관리자 비밀번호 : ${admin.apw }</td>
					<td>
						<button class="btn" onclick="location='${conPath}/adminDelete.do?aid=${admin.aid }'">추방</button>
					</td>
				</tr>				
			</c:forEach>
		</table>
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>