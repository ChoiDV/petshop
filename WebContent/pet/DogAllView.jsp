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
  <link href="${conPath }/css/list.css" rel="stylesheet">
 <style>
 
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
  	<c:if test="${not empty DogInsertResult }">
  		<script>
  			alert('${DogInsertResult }');
  		</script>
  	</c:if>
  	<c:if test="${not empty DogInsertError }">
  		<script>
  			alert('${DogInsertError }');
  			history.back();
  		</script>
  	</c:if>
	<jsp:include page="../main/header.jsp" />
		<table id="dogList">
			<c:if test="${dogList.size() eq 0 }">
				<tr>
					<td id="emptyDog" colspan="4">등록된 Cute Dog가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${dogList.size() != 0 }">
				<tr>
					<c:set var="i" value="0"/>
					<c:forEach var="dog" items="${dogList }"> 
						<td>
							<a href="${conPath }/DogContentView.do?pageNum=${pageNum }&dnum=${dog.dnum } ">
								<img src="${conPath }/dogImage/${dog.dimage1 }" class="mainimage" alt="대표사진"  >
								<div class="dogname">${dog.dname }</div>
								<div class="dogbreed">${dog.dbreedname }</div>
							</a>
						</td>
						<c:if test="${i%4 == 3 and i!=11}">
							</tr><tr>
						</c:if>
						<c:set var="i" value="${i+1 }"/>
					</c:forEach>
				</tr>			
			</c:if>
		</table>
		<div class="paging">
	 	<c:if test="${startPage > BLOCKSIZE}">
	 		 <a href="${conPath }/DogAllView.do?pageNum=1">&lt;&lt;</a> 
	 		 <a href="${conPath }/DogAllView.do?pageNum=${startPage-1 }">&lt;</a> 
	 	</c:if>
	 	<c:forEach var="i" begin="${startPage }" end="${endPage }">
	 		<c:if test="${i eq pageNum }">
	 			[ <b> ${i } </b> ]
	 		</c:if>
	 		<c:if test="${i != pageNum }">
	 			[ <a href="${conPath }/DogAllView.do?pageNum=${i }">${i }</a> ]
	 		</c:if>
	 	</c:forEach>
	 	<c:if test="${endPage < pageCnt }">
	 		[ <a href="${conPath }/DogAllView.do?pageNum=${endPage+1 }">&gt;</a> ]
	 		[ <a href="${conPath }/DogAllView.do?pageNum=${pageCnt}">&gt;&gt;</a> ]
	 	</c:if>
	 </div> 
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>

