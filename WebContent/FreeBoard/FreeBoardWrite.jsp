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
	<div class="form">
		<form action="${conPath }/freeBoardWrite.do" method="post" enctype="multipart/form-data">
			<table>
				<caption>Express</caption>
				<tr>
					<td>
						<input type="text" name="ftitle" placeholder="제목을 입력해 주세요.">
					</td>
				</tr>
				<tr>
					<td>
						<textarea rows="10" cols="10" name="fcontent" placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="ffilename1">
						<input type="file" name="ffilename2">
						<input type="file" name="ffilename3">
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
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>