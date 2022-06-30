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
<style>

</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="Form">
		<jsp:include page="../main/MyPageMenu.jsp"/>
		<div id="Message">
			<c:if test="${ModifyAllResult eq 1 }">
				<script>
					alert('개인정보 변경 성공');
				</script>
			</c:if>
			<c:if test="${ModifyAllResult eq 0 }">
				<script>
					alert('개인정보 변경 실패');
					history.back();
				</script>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>