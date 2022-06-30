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
  	<c:if test="${empty modifyAllResult }">
		<script>
			alert('비밀번호 확인해주세요');
			history.back();
		</script>
  	</c:if>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${modify eq 'all' }">
	<div id="Form">
		<jsp:include page="../main/MyPageMenu.jsp"/>
		<form action="${conPath }/modify.do" method="post">
		<input type="hidden" name="modify" value="${modify }">
		<table>
   			<caption>정보 수정</caption>
   			<tr>
   				<th>아이디</th>
   				<td>
   					<input type="text" name="mid" value="${modifyAllResult.mid }" readonly="readonly">
   				</td>
   			</tr>
   			<tr>
   				<th>이름</th>
   				<td>
   					<input type="text" name="mname" value="${modifyAllResult.mname }" required="required">
   				</td>
   			</tr>
   			<tr>
   				<th>전화번호</th>
   				<td>
   					<input type="text" name="mtel" value="${modifyAllResult.mtel }">
   				</td>
   			</tr>
   			<tr>
   				<th>메일</th>
   				<td>
   					<input type="email" name="memail" value="${modifyAllResult.memail }">
   				</td>
   			</tr>  
   			<tr>
   				<th>주소</th>
   				<td>
   					<input type="text" name="maddress" value="${modifyAllResult.maddress }">
   				</td>
   			</tr>
   			<tr>
   				<td colspan="2">
	   				<input type="submit" value="정보수정">
	   				<input type="button" value="취소" onclick="history.back()">
   				</td>
   			</tr>
   		</table>
   		</form>
	</div>
	</c:if>
	<c:if test="${modify eq 'id' }">   <!--  아이디 일 경우  -->
	<div id="Form">
		<jsp:include page="../main/MyPageMenu.jsp"/>
		<form action="${conPath }/modify.do" method="post">
			<input type="hidden" name="modify" value="${modify }">
		<table>
   			<caption>id 변경</caption>
   			<tr>
   				<th>아이디</th>
   				<td>
   					<input type="text" name="mid" value="${modifyAllResult.mid }" readonly="readonly">
   				</td>
   			</tr>  		
   			<tr>
   				<td colspan="2">
	   				<input type="submit" value="정보수정">
	   				<input type="button" value="취소" onclick="history.back()">
   				</td>
   			</tr>
   		</table>
   		</form>
	</div>
	</c:if>
	<c:if test="${modify eq 'pw' }">   <!--  비밀번호 변경일 경우  -->
	<div id="Form">
		<jsp:include page="../main/MyPageMenu.jsp"/>
		<form action="${conPath }/modify.do" method="post">
		<input type="hidden" name="modify" value="${modify }">
		<table>
   			<caption>pw 변경</caption>
   			<tr>
   				<th>현재 비밀번호</th>
   				<td>
   					<input type="text" name="mid" " readonly="readonly">
   				</td>
   			</tr>
   			<tr>
   				<td colspan="2">
	   				<input type="submit" value="정보수정">
	   				<input type="button" value="취소" onclick="history.back()">
   				</td>
   			</tr>
   		</table>
   		</form>
	</div>
	</c:if>
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>