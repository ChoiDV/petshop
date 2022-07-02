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
  <link href="${conPath }/css/mainList.css" rel="stylesheet">
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
	<table id="dogList">
			<c:if test="${mainList.size() eq 0 }">
				<tr>
					<td id="emptyDog" colspan="4">등록된 Cute Dog가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${mainList.size() != 0 }">
				<tr>
					<c:set var="i" value="0"/>
					<c:forEach var="dog" items="${mainList }"> 
						<td>
							<a href="${conPath }/DogContentView.do?dnum=${dog.dnum } ">
								<img src="${conPath }/DogImageUpFolder/${dog.dimage1 }" class="mainimage" alt="대표사진"  >
								<div class="dogname">${dog.dname }</div>
								<div class="dogbreed">${dog.dbreedname }</div>
							</a>
						</td>
						<c:if test="${i%3 == 2 and i!=8}">
							</tr><tr>
						</c:if>
						<c:set var="i" value="${i+1 }"/>
					</c:forEach>
				</tr>			
			</c:if>
		</table>
	<jsp:include page="../main/footer.jsp"/>
	
  </body>
</html>

