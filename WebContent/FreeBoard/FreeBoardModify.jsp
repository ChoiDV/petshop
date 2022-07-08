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
  		<form action="${conPath }/freeBoardModify.do" method="post" enctype="multipart/form-data">
  			<input type="hidden" name="pageNum" value="${pageNum }">
  			<input type="hidden" name="fnum" value="${fnum }">
  			<table class="input_form">
  				<caption>MODIFY</caption>
  				<tr>
  					<th>작성자 아이디</th>
  					<td>
  						<input type="text" name="mid" value="${freeBoardContent.mid }" readonly="readonly">
  					</td>
  				</tr>
  				<tr>
  					<th>제목</th>
  					<td>
  						<input type="text" name="ftitle" value="${freeBoardContent.ftitle }" required="required">
  					</td>
  				</tr>  
  				<tr>
  					<th>본문</th>
  					<td>
  						<textarea rows="5" cols="5" name="fcontent">${freeBoardContent.fcontent }</textarea>
  					</td>
  				</tr>
  				<tr>
  					<th>첨부파일</th>
  					<td>
  						<input type="file" name="ffilename1">
  						<input type="file" name="ffilename2">
  						<input type="file" name="ffilename3">
  					</td>
  				</tr>	
  				<tr>
  					<td>
  						<input type="submit" value="수정" class="btn">
  						<input type="reset" value="취소"  class="btn" onclick="history.back();">		
  					</td>
  				</tr>
  			</table>
  		</form>
  	</div>
  	<jsp:include page="../main/footer.jsp"/>
	
  </body>
</html>