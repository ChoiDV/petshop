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
	    *{
            list-style:none;
            text-decoration:none;
            margin:0;
            padding:0;
            color:black;           
        }
        #navigation {
            width: 300px;
            height: 420px;
            margin: 150px 0 0 0px;
            font-size: 1.5em;
            padding-top: 20px;
            padding-left: 20px;
            
        }
        #navigation .menu {
            font-weight: bold;
        }
        #navigation .menu .submenu {
            margin-left:40px;
        }
        #navigation .menu .submenu li {
            margin:5px 0;
            font-weight:normal;
        }
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<div id="navigation">
		<ul class="menu">
			<li><a href="#">Cute Dog</a>
				<ul class="submenu">
					<li><a href="${conPath }/DogInsertView.do">강아지 분양글 등록</a></li>  
					<li><a href="${conPath }/adminPageDogListView.do">강아지 분양글 수정</a></li>
					<li><a href="${conPath }/adminPageDogListView.do">강아지 분양글 삭제</a></li>  
				</ul></li>
			<li><a href="#">Pretty Cat</a>
				<ul class="submenu">
					<li><a href="#">고양이 분양글 등록</a></li>
					<li><a href="#">고양이 분양글 수정 </a></li>
					<li><a href="#">고양이 분양글 삭제</a></li>
				</ul></li>
			<c:if test="${not empty admin and (admin.aid eq 'admin' )}"> 
			<li><a href="#">관리자/회원 관리</a>
				<ul class="submenu">
					<li><a href="#">회원 추방</a></li>
					<li><a href="${conPath }/adminJoinView.do">관리자 등록</a>
					<li><a href="#">관리자 추방</a></li>
				</ul></li>
			</c:if>	
		</ul>
	</div>

</body>
</html>