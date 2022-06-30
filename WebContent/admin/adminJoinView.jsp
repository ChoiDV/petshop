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
  <link href="${conPath }/css/join.css" rel="stylesheet">
 <style>
 	.bigCap {
 		margin-bottom :100px;
 	}
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
  <jsp:include page="../main/header.jsp" />
  		<div id="join_form">
  			<form action="${conPath }/adminJoin.do" method="post">
  				<table>
  				 <caption>관리자 등록</caption>
                 <caption class="bigCap">Register as an administrator</caption>
  					<tr>
  						<th>관리자 아이디</th>
  						<td>
  							<input type="text" name="aid" required="required">
  						</td>
  					</tr>
  					<tr>
  						<th>관리자 비밀번호</th>
  						<td>
  							<input type="password" name="apw" required="required">
  						</td>
  					</tr>
  					<tr>
  						<th>관리자 비밀번호 재확인</th>
  						<td>
  							<input type="password" name="apwChk" required="required">
  							<div>비밀번호 확인 메세지</div>
  						</td>
  					</tr>
  					<tr>
  						<th>관리자 이름</th>
  						<td>
  							<input type="text" name="aname" required="required">
  						</td>
  					</tr>											
  					<tr>
  						<td colspan="2">
  							<input type="submit" value="관리자 등록" class="btn">
  							<input type="button" value="뒤로가기" class="btn" onclick="history.back();" >
  						</td>
  					</tr>
  				</table>
  			</form>
  		</div>
  		<jsp:include page="../main/footer.jsp" />
	
  </body>
</html>

