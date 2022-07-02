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
 
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
  <jsp:include page="../main/header.jsp" />
  <div id="AllForm">
	<table id="bar">
		<tr>
			<td>
				글번호 :${dogContent.dnum }
			</td>
			<td>
				등록일자 : ${dogContent.drdate }
			</td>
			<td>
				조회수 : ${dogContent.dhit }
			</td>
		</tr>
	</table>  
	<div id="dogContent_Form">
        <div id="profile"> 프로필</div>
        <div id="profile_eng"> All PET PROFILE </div>
        <div class="dogimage">  <img src="${conPath }/DogImageUpFolder/${dogContent.dimage1 }" alt="dimage1" /></div>
        <div class="dogimage">  <img src="${conPath }/DogImageUpFolder/${dogContent.dimage2 }" alt="dimage2" /></div>
        <div class="dogimage">  <img src="${conPath }/DogImageUpFolder/${dogContent.dimage3 }" alt="dimage3" /></div>
        <div id="CuteDog"> Cute Dog </div>
        <table id="mainContent">
            <tr>
                <th>이름</th>
                <td>
                    ${dogContent.dname }
                </td>
            </tr>
            <tr>
                <th>견종</th>
                <td>
                   ${dogContent.dbreedname }
                </td>
            </tr>
            <tr>
                <th>담당 브리더</th>
                <td>
                   ${aname } 님
                </td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                   <c:if test="${dogContent.dgender eq 'M'}">
						<span>남아</span><img src="${conPath }/img/dogM.jpg" alt="강아지 발자국" class="dogfoot" />
					</c:if>
					<c:if test="${dogContent.dgender eq 'F'}">
						<span>여아</span><img src="${conPath }/img/dogF.png" class="dogfoot" />
					</c:if>
                </td>
            </tr>
            <tr>
                <th>생일</th>
                <td>
                   	 ${dogContent.dbirth } 출생 (
					<c:if test="${dogYear != 0}">
						${dogYear }년
					</c:if>
						${dogAge } 개월 )
                </td>
            </tr>
            <tr>
                <th>건강기록</th>
                <td>종합백신 1차/ 종합건강검진2회</td>
            </tr>
            <tr>
                <th>분양가</th>
                <td>
                   <fmt:formatNumber value="${dogContent.dprice }" pattern="#,###"/>만원
                </td>
            </tr>
            <tr>
	        	<td colspan="2">
	        		<button>분양받기</button>
	        		<button>예약하기</button>
	        		<button onclick="location.href='${conPath }/DogAllView.do?pageNum=${pageNum }'">목록</button>
	        	</td>
	        </tr>
        </table>
        <div id="precontent">
        	<pre>${dogContent.dcontent }</pre>
        <div>
        <div class="dogimage">  <img src="${conPath }/dogImage/${dogContent.dimage4 }" alt="dimage4" /></div>
        <div class="dogimage">  <img src="${conPath }/dogImage/${dogContent.dimage5 }" alt="dimage5" /></div>
    	</div>
    </div>
    </div>
    </div>
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>