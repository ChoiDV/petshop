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
  <link href="${conPath }/css/content.css" rel="stylesheet">
 <style>
	.reply {
		margin-top:150px;
	}
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
	<c:if test="${searchResult.size() eq 0 }">
		<script>
			alert('가입된 회원이 없습니다. 다시 입력해주세요.');
			history.back();
		</script>
	</c:if>
	<c:if test="${searchResult.size() eq 1 }">
		<script>
			alert('회원님의 아이디는 : ${searchResult.get(0).mid }입니다.');
			answer = confirm('비밀번호를 찾으시겠습니까? (아니요 시 로그인 페이지로 이동 )');
			if(answer){
				location.href="${conPath }/searchPwView.do?mid=${searchResult.get(0).mid}";
			} else {
				location.href="${conPath }/loginView.do?mid=${searchResult.get(0).mid }";
			}
			
		</script>
	</c:if>
	<c:if test="${searchResult.size() > 1 }">
		<jsp:include page="../main/header.jsp"/>
	  <div class="reply"> 
		<table class="reply_content">
			<c:forEach var="list" items="${searchResult }">
				<tr>
					<td>이름 :${list.mname } </td>
					<td>아이디 : ${list.mid }</td>
					<td>생년월일 :${list.mbirth }</td>
					<td>성별 : ${list.mgender }</td>
					<td>전화번호 : ${list.mtel }</td>
					<td>					
						<button onclick="location='${conPath }/searchPwView.do?mid=${list.mid }'" class="rbtn">비밀번호 찾기</button>
						<button onclick="location='${conPath }/loginView.do?mid=${list.mid }'" class="rbtn">로그인</button>
					</td>
				</tr>				
			</c:forEach>
		</table>
		</div>
		<jsp:include page="../main/footer.jsp"/>
	</c:if>
  </body>
</html>