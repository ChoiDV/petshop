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
  <link href="${conPath }/css/freeboardwrite.css" rel="stylesheet">
 <style>
 
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
	 <c:if test="${empty member and empty admin }">
  		<script>
  			alert('글쓰기는 로그인 후 이용 가능합니다.');
  			location.href='${conPath }/loginView.do';
  		</script>
  </c:if>
  <c:if test="${not empty admin }">
  	<script>
  		alert('관리자는 Communication 의 글쓰기 기능을 사용하실수 없습니다.');
  		history.back();
  	</script>
  </c:if>
  <jsp:include page="../main/header.jsp"/>
  	<c:if test="${empty reply }">
	<div class="form">
		<form action="${conPath }/ReviewWrite.do" method="post" enctype="multipart/form-data">
			<table class="input_form">
				<caption>Review</caption>
				<tr>
					<td>
						<input type="text" name="rtitle" placeholder="제목을 입력해 주세요." required="required">
					</td>
				</tr>
				<tr>
					<td>
						<b>분양 해주신 브리더님</b>
						<c:forEach var="admin" items="${adminList }">
							<input type="radio" name="aid" value="${admin.aid }">${admin.aname }						
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>
						<textarea rows="10" cols="10" name="rcontent" placeholder="내용을 입력하세요" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="rfilename1">
						<input type="file" name="rfilename2">
						<input type="file" name="rfilename3">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="등록">
						<input type="button" value="뒤로가기" onclick='history.back();'>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</c:if>
	<c:if test="${not empty reply }">
		<div class="form">
		<form action="${conPath }/ReviewReplyWrite.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="rnum" value="${reply.rnum }">
			<table class="input_form">
				<caption>${reply.rtitle } 의 Express</caption>
				<tr>
					<td>
						<input type="text" name="rtitle" placeholder="제목을 입력해 주세요.">
					</td>
				</tr>
				<tr>
					<td>
						<textarea rows="10" cols="10" name="rcontent" placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="rfilename1">
						<input type="file" name="rfilename2">
						<input type="file" name="rfilename3">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="답변글 등록">
						<input type="button" value="뒤로가기" onclick='history.back();'>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</c:if>
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>