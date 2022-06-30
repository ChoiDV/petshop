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
  	<c:if test="${loginErrorMsg != null }">
  		<script>
	  		alert('${loginErrorMsg }');
	  		history.back();
  		</script>
  	</c:if>
  	<c:if test="${not empty adminJoinResult }">
  		<script>
  			alert('${adminJoinResult }');
  		</script>
  	</c:if>
  	<c:if test="${not empty adminJoinError }">
  		<script>
  			alert('${adminJoinError }');
  			history.back();
  		</script>
  	</c:if>
  	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
			<tr>
				<td>
					main
				</td>
			</tr>
			<tr>
				<td>
					main
				</td>
			</tr>
			<tr>
				<td>
					main
				</td>
			</tr>
			<tr>
				<td>
					main
				</td>
			</tr>
			<tr>
				<td>
					main
				</td>
			</tr>
			
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
	
  </body>
</html>

