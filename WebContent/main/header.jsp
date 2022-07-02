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
<link href="${conPath }/css/header.css" rel="stylesheet">
<style>
.searchimg {
	width: 30px;
}
.searchbutton {
	border:none;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('li#bookmark').click(function(){
			alert('ctrl+D 를 누르세요.');
		});
	});
</script>
</head>
<body>
	<c:if test="${empty member and empty admin}">  <!--  처음 시작했을떄   비회원 -->
	<header>
		<div id="gnb">
			<div id="right_gnb">
				<ul>
					<li id="bookmark">+BookMark</li>
					<li><a href="${conPath }/joinAgree.do">회원가입</a></li>
					<li><a href="${conPath }/loginView.do">로그인</a></li>
				</ul>
			</div>
			<div id="left_gnb">
				<ul>
					<li><input type="text" name="search" />
					<button class="searchbutton">
							<img src="${conPath }/img/돋보기1.PNG" class="searchimg">
						</button></li>
				</ul>
			</div>
			<div id="logo">
				<a href="${conPath }/main.do">로고</a>
			</div>
		</div>
		<div id="lnb">
			<ul>
				<li>회사 소개
					<ol class="subMenu">
						<li><a href="#"><img src="${conPath }/img/대표 인사말.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/회사소개.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/브리더소개.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/가맹문의.png" /></a></li>
						<li class="text">대표 인사말</li>
						<li class="text">회사 소개</li>
						<li class="text">브리더 소개</li>
						<li class="text">가맹 문의</li>
					</ol>
				</li>
				<li>Cute Dog
					<ol class="subMenu">
						<li><a href="${conPath }/DogAllView.do"><img src="${conPath }/img/전체보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/프리미엄.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/견종별 모아보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/지점별 모아보기.jpg" /></a></li>
						<li class="text">전체 보기</li>
						<li class="text">프리 미엄</li>
						<li class="text">견종별 모아보기</li>
						<li class="text">지점별 모아보기</li>
					</ol>
				</li>
				<li>Pretty Cat
					<ol class="subMenu">
						<li><a href="#"><img src="${conPath }/img/고양이전체보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/고양이프리미엄.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/묘종별모아보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/지점별 모아보기.jpg" /></a></li>
						<li class="text">전체 보기</li>
						<li class="text">프리 미엄</li>
						<li class="text">묘종별 모아보기</li>
						<li class="text">지점별 모아보기</li>
					</ol>
				</li>
				<li>입양 후기
					<ol class="subMenu">
						<!-- 입양 후기 게시판 -->
						<li></li>
						<li></li>
						<li><a href="#"><img src="${conPath }/img/실제분양후기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/실제분양후기.jpg" /></a></li>
						<li class="text"></li>
						<li class="text"></li>
						<li class="text">실제분양후기</li>
						<li class="text">추가로 하나 넣기</li>
					</ol>
				</li>
				<li><a href="${conPath }/freeBoardListView.do">Communication</a></li>
			</ul>
		</div>
	</header>
	</c:if>
	<c:if test="${not empty member and empty admin}">   <!--  회원 로그인 했을때  -->
	<header>
		<div id="gnb">
			<div id="right_gnb">
				<ul>
					<li id="bookmark">+BookMark</li>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>
					<li><a href="${conPath }/mypageView.do">마이페이지</a></li>
					<li><b>${member.mname } 님</b></li>
				</ul>
			</div>
			<div id="left_gnb">
				<ul>
					<li><input type="text" name="search" />
					<button class="searchbutton">
							<img src="${conPath }/img/돋보기1.PNG" class="searchimg">
						</button></li>
				</ul>
			</div>
			<div id="logo">
				<a href="${conPath }/main.do">로고</a>
			</div>
		</div>
		<div id="lnb">
			<ul>
				<li>회사 소개
					<ol class="subMenu">
						<li><a href="#"><img src="${conPath }/img/대표 인사말.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/회사소개.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/브리더소개.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/가맹문의.png" /></a></li>
						<li class="text">대표 인사말</li>
						<li class="text">회사 소개</li>
						<li class="text">브리더 소개</li>
						<li class="text">가맹 문의</li>
					</ol>
				</li>
				<li>Cute Dog
					<ol class="subMenu">
						<li><a href="${conPath }/DogAllView.do"><img src="${conPath }/img/전체보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/프리미엄.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/견종별 모아보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/지점별 모아보기.jpg" /></a></li>
						<li class="text">전체 보기</li>
						<li class="text">프리 미엄</li>
						<li class="text">견종별 모아보기</li>
						<li class="text">지점별 모아보기</li>
					</ol>
				</li>
				<li>Pretty Cat
					<ol class="subMenu">
						<li><a href="#"><img src="${conPath }/img/고양이전체보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/고양이프리미엄.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/묘종별모아보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/지점별 모아보기.jpg" /></a></li>
						<li class="text">전체 보기</li>
						<li class="text">프리 미엄</li>
						<li class="text">묘종별 모아보기</li>
						<li class="text">지점별 모아보기</li>
					</ol>
				</li>
				<li>입양 후기
					<ol class="subMenu">
						<!-- 입양 후기 게시판 -->
						<li></li>
						<li></li>
						<li><a href="#"><img src="${conPath }/img/실제분양후기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/실제분양후기.jpg" /></a></li>
						<li class="text"></li>
						<li class="text"></li>
						<li class="text">실제분양후기</li>
						<li class="text">추가로 하나 넣기</li>
					</ol>
				</li>
				<li><a href="${conPath }/freeBoardListView.do">Communication</a></li>
			</ul>
		</div>
	</header>
	</c:if>
	<c:if test="${not empty adminLoginResult}">
		<script>
			alert('${adminLoginResult}');
		</script>
	</c:if>
	<c:if test="${not empty admin && empty member}">  <!--  관리자 로그인 성공 했을시에만  -->
		
		<header>
		<div id="gnb">
			<div id="right_gnb">
				<ul>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>
					<li><a href="${conPath }/adminPageView.do">관리자 마이페이지</a></li>  <!--  관리자 전용 마이페이지  각 관리자가 등록한 동물들 출력해주기  -->
					<li><b>관리자 ${admin.aname } 님</b></li>
				</ul>
			</div>
			<div id="left_gnb">
				<ul>
					<li><input type="text" name="search" />
					<button class="searchbutton">
							<img src="${conPath }/img/돋보기1.PNG" class="searchimg">
						</button></li>
				</ul>
			</div>
			<div id="logo">
				<a href="${conPath }/main.do">로고</a>
			</div>
		</div>
		<div id="lnb">
			<ul>
				<li>회사 소개
					<ol class="subMenu">
						<li><a href="#"><img src="${conPath }/img/대표 인사말.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/회사소개.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/브리더소개.png" /></a></li>
						<li><a href="#"><img src="${conPath }/img/가맹문의.png" /></a></li>
						<li class="text">대표 인사말</li>
						<li class="text">회사 소개</li>
						<li class="text">브리더 소개</li>
						<li class="text">가맹 문의</li>
					</ol>
				</li>
				<li>Cute Dog
					<ol class="subMenu">
						<li><a href="${conPath }/DogAllView.do"><img src="${conPath }/img/전체보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/프리미엄.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/견종별 모아보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/지점별 모아보기.jpg" /></a></li>
						<li class="text">전체 보기</li>
						<li class="text">프리 미엄</li>
						<li class="text">견종별 모아보기</li>
						<li class="text">지점별 모아보기</li>
					</ol>
				</li>
				<li>Pretty Cat
					<ol class="subMenu">
						<li><a href="#"><img src="${conPath }/img/고양이전체보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/고양이프리미엄.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/묘종별모아보기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/지점별 모아보기.jpg" /></a></li>
						<li class="text">전체 보기</li>
						<li class="text">프리 미엄</li>
						<li class="text">묘종별 모아보기</li>
						<li class="text">지점별 모아보기</li>
					</ol>
				</li>
				<li>입양 후기
					<ol class="subMenu">
						<!-- 입양 후기 게시판 -->
						<li></li>
						<li></li>
						<li><a href="#"><img src="${conPath }/img/실제분양후기.jpg" /></a></li>
						<li><a href="#"><img src="${conPath }/img/실제분양후기.jpg" /></a></li>
						<li class="text"></li>
						<li class="text"></li>
						<li class="text">실제분양후기</li>
						<li class="text">추가로 하나 넣기</li>
					</ol>
				</li>
				<li><a href="${conPath }/freeBoardListView.do">Communication</a></li>
			</ul>
		</div>
	</header>	
	</c:if>
	<c:if test="${not empty adminLoginError }">   <!--  관리자 로그인 실패시  -->
		<script>
			alert('${adminLoginError }');
			history.back();
		</script>
	</c:if>

</body>
</html>