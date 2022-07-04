<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
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
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<form action="${conPath }/modifyDogReply.do" method="post" >
    		<input type="hidden" name="dnum" value="${dogContent.dnum }">
    		<input type="hidden" name="replyPageNum" value="${replyPageNum }">
    		<input type="text" name="mid" value="${mid }" readonly="readonly" >
    		<input type="text" name="reply_content" value="${reply_Content }">
    		<input type="submit" value="등록">
    	</form>
    	
</body>
</html>