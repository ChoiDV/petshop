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
 	.button {
 		text-align: center;
 		background-color:#ffd1dc;	
 		height:30px;
 		line-height:30px;
 		
 	}
 	.button button {
 		background-color:#ffd1dc;	
 		border:2px solid white;
 		color:white;
 		font-weight:bold;
 		 box-shadow: 0 8px 20px 0 rgba(0,0,0,0.15);
 		 width:50px;
 		
 	}
 	
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
  <c:if test="${DogInsertResult eq 1 }">
  		<script>
  			alert('강아지 등록 성공 ');
  			location.href="${conPath }/adminPageDogListView.do";
  		</script>
  	</c:if>
  	<c:if test="${DogInsertResult eq 0 }">
  		<script>
  			alert('강아지 등록 실패');
  			history.back();
  		</script>
  	</c:if>
  	<c:if test="${deleteResult eq 1 }">
  		<script>
  			alert('강아지 분양글 삭제가 완료되었습니다.');
  		</script>
  	</c:if>
  	<c:if test="${deleteResult eq 0 }">
  		<script>
  			alert('강아지 분양글 삭제에 실패하였습니다.');
  			history.back();
  		</script>
  	</c:if>
  	<c:if test="${DogModifyResult eq 1 }">
  		<script>
  			alert('강아지 수정 완료');
  		</script>
  	</c:if>
  	<c:if test="${DogModifyResult eq 0 }" >
  		<script>
  			alert('강아지 수정 실패');
  			history.back();
  		</script>
  	</c:if>
	<jsp:include page="../main/header.jsp" />
		<table id="List">
			<c:if test="${dogList.size() eq 0 }">
				<tr>
					<td id="empty" colspan="4">등록된 Cute Dog가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${dogList.size() != 0 }">
				<tr>
					<c:set var="i" value="0"/>
					<c:forEach var="dog" items="${dogList }"> 
						<td>
							<a href="${conPath }/DogContentView.do?pageNum=${pageNum }&dnum=${dog.dnum } ">
								<img src="${conPath }/DogImageUpFolder/${dog.dimage1 }" class="mainimage" alt="대표사진"  >
								<div class="name">${dog.dname }</div>
								<div class="breed">${dog.dbreedname }</div>
							</a> 
 							<c:if test="${( admin.aid eq 'admin') or (admin.aid eq dog.aid ) }" > 
								<div class="button">
									<button onclick="location='${conPath }/DogModifyView.do?dnum=${dog.dnum }&pageNum=${pageNum }'">수정</button>
									<button onclick="location='${conPath }/DogDeleteView.do?dnum=${dog.dnum }'">삭제</button>
								</div>
 							</c:if> 
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
		 		 <a href="${conPath }/adminPageDogListView.do?pageNum=1">&lt;&lt;</a> 
		 		 <a href="${conPath }/adminPageDogListView.do?pageNum=${startPage-1 }">&lt;</a> 
		 	</c:if>
		 	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		 		<c:if test="${i eq pageNum }">
		 			[ <b> ${i } </b> ]
		 		</c:if>
		 		<c:if test="${i != pageNum }">
		 			[ <a href="${conPath }/adminPageDogListView.do?pageNum=${i }">${i }</a> ]
		 		</c:if>
		 	</c:forEach>
		 	<c:if test="${endPage < pageCnt }">
		 		[ <a href="${conPath }/adminPageDogListView.do?pageNum=${endPage+1 }">&gt;</a> ]
		 		[ <a href="${conPath }/adminPageDogListView.do?pageNum=${pageCnt}">&gt;&gt;</a> ]
		 	</c:if>
	 	</div> 
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>