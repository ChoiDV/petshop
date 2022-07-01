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
  <link href="${conPath }/css/modify.css" rel="stylesheet">
 <style>
 
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		$('form').submit(function(){
 			var chk = $('#chk').val();
 			if(chk == 'all'){
 				$('form').attr('action','${conPath }/modifyAllView.do?modify=${param.modify }');
 			} else if(chk == 'pw'){
 				$('form').attr('action','${conPath }/modifyAllView.do?modify=${param.modify }');
 			}
 		});
 	});
 </script>  
</head>
  <body>
	<jsp:include page="../main/header.jsp" />
	<div class="form">
		<jsp:include page="../main/MyPageMenu.jsp"/>	
		<form action="${conPath }/modifyAllView.do" method="post">
			<input type="hidden" id="chk" name="chK" value="${param.modify }">
			<table id="checkPassword">
			<caption>비밀번호 확인</caption>
			 <tr>
			 	<th>비밀번호</th>
			 	<td>
			 		<input type="password" name="mpw">
			 	</td>
			 </tr>
			 <tr>
			 	<td colspan="2">
			 		<input type="submit" value="확인">
			 	</td>
			 </tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>