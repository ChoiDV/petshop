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
			<c:if test="${ModifyPwResult eq 1 }">
				<script>
					alert('비밀번호 변경 성공 다시 로그인 후 서비스를 이용해주세요.');
					location.href="${conPath }/loginView.do";
				</script>
			</c:if>
			<c:if test="${ModifyPwResult eq 0  }">
				<script>
					alert('현재 비밀번호를 확인해주세요 ');
				</script>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>