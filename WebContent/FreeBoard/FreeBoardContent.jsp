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
  <jsp:include page="../main/header.jsp"/>
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
  	<div id="top_bar">
		<span>글번호 : ${freeContent.fnum }</span><span>작성일 : ${freeContent.frdate }</span><span>조회수 : ${freeContent.fhit }</span>
	</div>
	<div id="file_bar">
		<c:if test="${empty freeContent.ffilename1 and empty freeContent.ffilename2 and empty freeContent.ffilename3 }" >
			<ul>
				<li>첨부파일 없음</li>
			</ul>
		</c:if>
		<c:if test="${freeContent.ffilename1 != null or freeContent.ffilename2 != null or freeContent.ffilename3 != null  }" >
			<ul id="file_menu">
				<li>
					첨부파일
					<ul id="files_menu">
					<c:if test="${freeContent.ffilename1 != null }">
						<li>${freeContent.ffilename1 }</li>
					</c:if>
					<c:if test="${freeContent.ffilename2 != null }">	
						<li>${freeContent.ffilename2 }</li>
					</c:if>	
					<c:if test="${freeContent.ffilename3 != null }">	
						<li>${freeContent.ffilename3 }</li>
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
			<td>${freeContent.mname }(${freeContent.mid })</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${freeContent.ftitle }</td>
		</tr>
		<tr>
			<th>본문</th>
			<td><pre>${freeContent.fcontent }</pre></td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${member.mid eq freeContent.mid }">
					<button onclick="location.href='${conPath }/freeBoardModifyView.do?fnum=${freeContent.fnum }&pageNum=${pageNum }'">수정</button>
				</c:if>
				<c:if test="${member.mid eq freeContent.mid or not empty admin }">
					<button onclick="location.href='${conPath }/freeBoardDeleteView.do?fnum=${freeContent.fnum }&pageNum=${pageNum }'">삭제</button>
				</c:if>
				<button onclick="location.href='${conPath }/freeBoardReplyView.do?fnum=${freeContent.fnum }&pageNum=${pageNum }'">답변</button>
				<button onclick="location.href='${conPath }/freeBoardListView.do?pageNum=${pageNum }'">목록</button>
			</td>
		</tr>
	</table>
	<jsp:include page="../main/footer.jsp"/>
  </body>
</html>