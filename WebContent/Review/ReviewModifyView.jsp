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
	  <jsp:include page="../main/header.jsp"/>
	<div class="form">
		<form action="${conPath }/ReviewModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${pageNum }">
  			<input type="hidden" name="rnum" value="${rnum }">
			<table class="input_form">
				<caption>Review Modify</caption>
				<tr>
					<td>
						<input type="text" name="rtitle" placeholder="제목을 입력해 주세요." required="required" value="${ReviewContent.rtitle }">
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
						<textarea rows="10" cols="10" name="rcontent" placeholder="내용을 입력하세요" required="required">${ReviewContent.rcontent }</textarea>
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
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>