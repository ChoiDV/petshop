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
 	.left{text-align: left;}
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		$('tr').click(function(){
 			var fnum = $(this).children().eq(0).text();
 			if ( !isNaN(Number(fnum))){
 				location.href='${conPath }/freeBoardContentView.do?fnum='+fnum+'&pageNum=${pageNum }';
 			}
 		});
 	});
 </script>  
</head>
  <body>
  <c:if test="${writeResult eq 1 }">
  	<script>
  		alert('글 등록 성공');
  	</script>
  </c:if>
  <c:if test="${writeResult eq 0 }">
  	<script>
  		alert('글 등록 실패');
  		history.back();
  	</script>
  </c:if>
  <c:if test="${deleteResult >= 1}">
  	<script>
  		alert('${deleteResult }개 글 삭제 완료되었습니다.');
  	</script>
  </c:if>
  <c:if test="${deleteResult eq 0 }">
  	<script>
  		alert('글 삭제에 실패하였습니다.');
  		history.back();
  	</script>
  </c:if>
  <c:if test="${ReplyResult eq '1' }" >
  	<script>
  		alert('답글 작성 성공!');
  	</script>
  </c:if>
  <c:if test="${ReplyResult eq '0' }">
	<script>
		alert('답글 작성 실패..');
		history.back();
	</script>  
  </c:if>
  <jsp:include page="../main/header.jsp"/>
  <div id="allform">
		<table>
			<caption>Communication</caption>
			<tr>
				<td>
					<a href="${conPath }/freeBoardWriteView.do">글 쓰기</a>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${freeList.size() eq 0 }">
				<tr>
					<td>
						작성된 글이 없습니다.
					</td>
				</tr>
			</c:if>
			<c:if test="${freeList.size() != 0 }">
				<c:forEach var="list" items="${freeList }">
					<tr>
						<td>${list.fnum }</td>
						<td>${list.mname }</td>
						<td class="left">
							<c:forEach var="i" begin="1" end="${list.findent }">
								<c:if test="${i eq list.findent }">
									└─
								</c:if>
								<c:if test="${i != list.findent }">
									&nbsp; &nbsp; &nbsp;
								</c:if>
							</c:forEach>
							${list.ftitle }
							<c:if test="${list.fhit > 20 }">
								<b> *hit* </b>
							</c:if>
						</td>
						<td><fmt:formatDate value="${list.frdate }" pattern="yy년MM월dd일(E)" /></td>
						<td>${list.fhit }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE}">
		 		 <a href="${conPath }/freeBoardListView.do?pageNum=1">&lt;&lt;</a> 
		 		 <a href="${conPath }/freeBoardListView.do?pageNum=${startPage-1 }">&lt;</a> 
		 	</c:if>
		 	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		 		<c:if test="${i eq pageNum }">
		 			[ <b> ${i } </b> ]
		 		</c:if>
		 		<c:if test="${i != pageNum }">
		 			[ <a href="${conPath }/freeBoardListView.do?pageNum=${i }">${i }</a> ]
		 		</c:if>
		 	</c:forEach>
		 	<c:if test="${endPage < pageCnt }">
		 		[ <a href="${conPath }/freeBoardListView.do?pageNum=${endPage+1 }">&gt;</a> ]
		 		[ <a href="${conPath }/freeBoardListView.do?pageNum=${pageCnt}">&gt;&gt;</a> ]
		 	</c:if>
		</div>
		</div>
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>