<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
footer {
	height: 100px;
	background-color: #ffd1dc;
	margin: 50px auto 0 auto;
}

footer #footer_conts, footer #footer_conts a {
	color: black;
	font-weight: blod;
	font-size: 0.9em;
	text-align: center;
}

footer #footer_conts p:first-child {
	font-weight: bold;
	height: 30px;
	line-height: 30px;
	margin-top: 40px;
}

#insertDog {
	font-weight: bold;
	font-size: 0.8em;
}
</style>
</head>
<body>
	<footer>
		<div id="footer_conts">
			<p style="background-color: #ffd1dc;">(주)반려 &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; TEL : &nbsp; 02-9876-9878 / 010-9876-9878</p>
			<p style="background-color: #ffd1dc;">
				서울특별시 어떤구 어떤로 9 좋은빌딩 1-5F |
				<c:if test="${empty member and empty admin}">
					<b><a href="${conPath }/adminLoginView.do"
						style="background-color: #ffd1dc;">관리자 모드</a></b>
				</c:if>
			</p>
			<p style="background-color: #ffd1dc;">Copyright© 2022 tj . All
				rights reserved.</p>
		</div>
	</footer>
</body>
</html>