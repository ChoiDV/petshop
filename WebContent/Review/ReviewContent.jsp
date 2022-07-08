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
  <link href="${conPath }/css/reviewContent.css" rel="stylesheet">
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
  	<c:if test="${empty member and empty admin }">
  		<script>
  			alert('로그인 후 이용하실수 있습니다.');
  			location.href="${conPath }/loginView.do";
  		</script>
  	</c:if>
  	<c:if test="${modifyResult eq 1 }">
  		<script>
  			alert('수정 성공하였습니다.');
  		</script>
  	</c:if>
  	<c:if test="${modifyResult eq 0 }">
  		<script>
  			alert('수정에 실패했습니다. 다시 시도해주세요');
  			history.back();
  		</script>
  	</c:if>
  	<div id="wrap">
  	<div id="top_bar">
		<span>글번호 : ${reviewContent.rnum }</span><span>작성일 : ${reviewContent.rrdate } <c:if test="${aname != null }">/ 담당자: ${aname } </c:if></span>
		<span>조회수 : ${reviewContent.rhit }</span>
	</div>
	<div id="file_bar">
		<c:if test="${empty reviewContent.rfilename1 and empty reviewContent.rfilename2 and empty reviewContent.rfilename3 }" >
			<ul>
				<li>첨부파일 없음</li>
			</ul>
		</c:if>
		<c:if test="${reviewContent.rfilename1 != null or reviewContent.rfilename2 != null or reviewContent.rfilename3 != null  }" >
			<ul id="file_menu">
				<li>
					첨부파일
					<ul id="files_menu">
					<c:if test="${reviewContent.rfilename1 != null }">
						<li>${reviewContent.rfilename1 }</li>
					</c:if>
					<c:if test="${reviewContent.rfilename2 != null }">	
						<li>${reviewContent.rfilename2 }</li>
					</c:if>	
					<c:if test="${reviewContent.rfilename3 != null }">	
						<li>${reviewContent.rfilename3 }</li>
					</c:if>		
					</ul>
				</li>
			</ul>
		</c:if>		
	</div>
	<table>
		<caption>View Details</caption>	
		<tr>
			<th>작성자</th>
			<td>${reviewContent.mname }(${reviewContent.mid })</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${reviewContent.rtitle }</td>
		</tr>		
		<tr>
			<th>본문</th>
			<td><pre>${reviewContent.rcontent }</pre></td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${member.mid eq reviewContent.mid }">
					<button onclick="location.href='${conPath }/ReviewModifyView.do?rnum=${reviewContent.rnum }&pageNum=${pageNum }'">수정</button>
				</c:if>
				<c:if test="${member.mid eq reviewContent.mid or not empty admin }">
					<button onclick="location.href='${conPath }/ReviewDeleteView.do?rnum=${reviewContent.rnum }&pageNum=${pageNum }'">삭제</button>
				</c:if>
				<button onclick="location.href='${conPath }/ReviewReplyWriteView.do?rnum=${reviewContent.rnum }&pageNum=${pageNum }'">답변</button>
				<button onclick="location.href='${conPath }/ReviewListView.do?pageNum=${pageNum }'">목록</button>
			</td>
		</tr>
	</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>