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
  <jsp:include page="../main/header.jsp" />
	<c:if test="${not empty joinResult }">
		<!--  회원가입 성공 -->
		<h2>회원가입이 성공되었습니다.</h2>
		<table>
			<tr>
				<th>아이디</th>
				<td>
					${join.mid }
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					${join.mname }
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					${join.mtel }
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					${join.mbirth }
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					${join.memail }
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					${join.maddress }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button onclick="location.href='${conPath }/loginView.do'">로그인</button>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${not empty joinErrorMsg }">
		<script>
			alert('${joinErrorMsg}');
			history.back();
		</script>
	</c:if>
  </body>
</html>

