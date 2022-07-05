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
  		<tr>
  		<c:set var="i" value="0"/>
  		<c:forEach var="dogbreed" items="${breedlist }">
  			<td>
 	 			<button onclick="location='${conPath }/DogDbreedListView.do?dbreedno=${dogbreed.dbreedno }'">${dogbreed.dbreedname }</button>
  			</td>
  			<c:if test="${i%5 == 4 and i != 19 }">
  				</tr><tr>
  			</c:if>
  			<c:set var="i" value="${i+1 }"/>
  		</c:forEach>
  		</tr>
  	</table>
  	<table id="List">
			<caption>"반려"의  ${dogList.dbreedname } 들</caption>
			<c:if test="${dogList.size() eq 0 }">
				<tr>
					<td id="empty" colspan="4">등록된 ${dogList.dbreedname }가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${dogList.size() != 0 }">
				<tr>
					<c:set var="j" value="0"/>
					<c:forEach var="dog" items="${dogList }"> 
						<td>
							<a href="${conPath }/DogContentView.do?pageNum=${pageNum }&dnum=${dog.dnum }">
								<img src="${conPath }/DogImageUpFolder/${dog.dimage1 }" class="mainimage" alt="대표사진"  >
								<div class="name">${dog.dname }</div>
								<div class="breed">${dog.dbreedname }</div>
							</a>
						</td>						
						<c:if test="${j%4 == 3 and j!=11}">
							</tr><tr>
						</c:if>
						<c:set var="j" value="${j+1 }"/>
					</c:forEach>
				</tr>		
			</c:if>
		</table>
  	<div class="paging">
		 	<c:if test="${startPage > BLOCKSIZE}">
		 		 <a href="${conPath }/DogBreedView.do?pageNum=1">&lt;&lt;</a> 
		 		 <a href="${conPath }/DogBreedView.do?pageNum=${startPage-1 }">&lt;</a> 
		 	</c:if>
		 	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		 		<c:if test="${i eq pageNum }">
		 			[ <b> ${i } </b> ]
		 		</c:if>
		 		<c:if test="${i != pageNum }">
		 			[ <a href="${conPath }/DogBreedView.do?pageNum=${i }">${i }</a> ]
		 		</c:if>
		 	</c:forEach>
		 	<c:if test="${endPage < pageCnt }">
		 		[ <a href="${conPath }/DogBreedView.do?pageNum=${endPage+1 }">&gt;</a> ]
		 		[ <a href="${conPath }/DogBreedView.do?pageNum=${pageCnt}">&gt;&gt;</a> ]
		 	</c:if>
	 	</div> 
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>