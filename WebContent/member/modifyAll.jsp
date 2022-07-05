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
<c:if test="${modify eq 'all' }">
	<link href="${conPath }/css/modify2.css" rel="stylesheet">
</c:if>
<c:if test="${modify eq 'pw'  }">
	<link href="${conPath }/css/modify3.css" rel="stylesheet">
</c:if>
<style>
.AllForm {
	height: 700px;
}

</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<c:if test="${empty modifyAll and empty modifyId and empty modifyPw}">
		<script>
			alert('비밀번호 확인해주세요');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div class="AllForm">
		<c:if test="${modify eq 'all' }">  
			<jsp:include page="../main/MyPageMenu.jsp" />
			<div id="form">
				<form action="${conPath }/modify.do" method="post"
					class="input_form">
					<input type="hidden" name="modify" value="${modify }">
					<table id="input_form">
						<caption>정보 수정</caption>
						<tr>
							<th>아이디</th>
							<td><input type="text" name="mid" value="${modifyAll.mid }"
								readonly="readonly"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" name="mname"
								value="${modifyAll.mname }" required="required"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" name="mtel"
								value="${modifyAll.mtel }"></td>
						</tr>
						<tr>
							<th>메일</th>
							<td><input type="email" name="memail"
								value="${modifyAll.memail }"></td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" name="maddress"
								value="${modifyAll.maddress }"></td>
						</tr>
						<tr>
							<td colspan="2">
							<input type="submit" value="정보수정" class="btn">
							 <input type="button" value="취소" onclick="history.back()" class="btn"></td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>

		<c:if test="${modify eq 'pw' }">  
			<!--  비밀번호 변경일 경우  -->
			<jsp:include page="../main/MyPageMenu.jsp" />
			<div id="form">
				<form action="${conPath }/modify.do" method="post"
					class="input_form">
					<input type="hidden" name="modify" value="${modify }">
					<table id="input_form">
						<caption>비밀번호 변경</caption>
						<tr>
							<th>현재 비밀번호</th>
							<td><input type="password" name="oldmpw"></td>
						</tr>
						<tr>
							<th>새 비밀번호</th>
							<td><input type="password" name="mpw"></td>
						</tr>
						<tr>
							<th>새 비밀번호 확인</th>
							<td><input type="password" name="mpwChk"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="비밀번호 변경" class="btn">
								<input type="button" value="취소" onclick="history.back()" class="btn">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>