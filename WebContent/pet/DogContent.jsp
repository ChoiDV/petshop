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
 *{
 	font-family: 'NanumSquareRoundLight';
	font-family: 'NanumSquareRound';
	font-family: 'NanumSquareRoundBold';
	font-family: 'NanumSquareRoundExtraBold';
 }
 .heart {
 	width:100px;
 }
 </style>
 <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
		$('.heart').click(function(){
			var zimno = Number('${ZimCount }');
			if(zimno==0){
				$(this).attr('src','img/빈하트.png');
				location.href='${conPath}/insertZim.do?dnum='+'${dogContent.dnum}&mid='+'${member.mid}';
			}else if(zimno>=1){
				zimno=0;
				$(this).attr('src','img/하트.png');
				location.href='${conPath}/deleteZim.do?dnum='+'${dogContent.dnum }&mid='+'${member.mid}';
			}
		});
	});
 </script>
</head>
  <body>
  <c:if test="${empty member and empty admin}">
  	<script>
  		alert('로그인 후 이용가능합니다.');
  		location.href="${conPath }/loginView.do";
  	</script>
  </c:if>
  <c:if test="${replyResult eq 1 }">
  	<script>
		alert('댓글 작성 성공');
  	</script>
  </c:if>
  <c:if test="${replyResult eq 0 }">
  	<script>
  		alert('댓글 작성 실패');
  	</script>  	
  </c:if>
  <c:if test="${DogReplyDeleteResult eq 1 }">
  	<script>
		alert('댓글 삭제 성공');
  	</script>
  </c:if>
  <c:if test="${DogReplyDeleteResult eq 0 }">
  	<script>
  		alert('댓글 삭제 실패');
  	</script>
  </c:if> 
  
  <c:if test="${zimResult eq 1 }">
  	<script>
		alert('찜목록 추가 성공 ');
  	</script>
  </c:if>
  <c:if test="${zimResult eq 0 }">
  	<script>
  		alert('찜목록 추가 실패');
  	</script>
  </c:if>
  
  <c:if test="${noZimResult eq 1 }">
  	<script>
		alert('찜목록 삭제 성공 ');
  	</script>
  </c:if>
  <c:if test="${noZimResult eq 0 }">
  	<script>
  		alert('찜목록 삭제 실패');
  	</script>
  </c:if>
   
  
  <jsp:include page="../main/header.jsp" />
  <div id="dogContent_Form">
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
        
        <div id="CuteDog"> Cute Dog </div>
        <div>
			<c:if test="${ZimCount eq 0 }">
				<img src="${conPath }/img/빈하트.png" class="heart">
			</c:if>
			<c:if test="${ZimCount >= 1 }">
				<img src="${conPath }/img/하트.png" class="heart">			
			</c:if>
        </div>
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
        <div id="dcontent">
        	<pre>${dogContent.dcontent }</pre>      
    	</div>
    	<c:if test="${dogContent.dimage2 != null }">
        	<div class="dogimage"><img src="${conPath }/DogImageUpFolder/${dogContent.dimage2 }" alt="dimage2" /></div>
        </c:if>
         <c:if test="${dogContent.dimage3 != null }">
        	<div class="dogimage"><img src="${conPath }/DogImageUpFolder/${dogContent.dimage3 }" alt="dimage3" /></div>
        </c:if>
        <c:if test="${dogContent.dimage4 != null }">
        	<div class="dogimage"><img src="${conPath }/DogImageUpFolder/${dogContent.dimage4 }" alt="dimage4" /></div>
        </c:if>
        <c:if test="${dogContent.dimage5 != null }">
        	<div class="dogimage"><img src="${conPath }/DogImageUpFolder/${dogContent.dimage5 }" alt="dimage5" /></div>
        </c:if>
        
    </div>  <!--  id=dogContent_Form -->
    </div>  <!--  all form -->
     <div class="reply"> 
        <c:if test="${empty admin }"> 
    	<form action="${conPath }/dogReplyInsert.do" method="post" > 
    		<input type="hidden" name="dnum" value="${dogContent.dnum }">
    		<input type="hidden" name="replyPageNum" value="${replyPageNum }"> 
  	<table> 
    		<caption>${dogContent.dname } 에게 한마디</caption>
    		<tr>
    			<td>
    				<input type="text" name="reply_content">
    				<input type="submit" value="등록">
    			</td>
    		</tr>
    	</table>
    	</form>
    	</c:if>
    	<table class="reply_content">
			<c:if test="${replyList.size() eq 0 }">
				<tr><td>등록된 댓글이 없습니다.</td></tr>
			</c:if>
			<c:if test="${replyList.size() != 0 }">
				<c:forEach var="reply" items="${replyList }"> 
					<tr>
						<td>${reply.rn }번 </td>
						<td>작성자 id : ${reply.mid } </td>
						<td>${reply.reply_content }</td>
						<td><fmt:formatDate value="${reply.rdate }" type="date" pattern="YY.MM.dd"/></td>
						<c:if test="${( member.mid eq reply.mid ) or not empty admin }">
							<td>
							<button class="rbtn" onclick="location='${conPath }/modifyDogReplyView.do?rno=${reply.rno }&dnum=${dogContent.dnum }&replyPageNum=${replyPageNum }'">수정</button>
							<button class="rbtn" onclick="location='${conPath }/deleteDogReplyView.do?rno=${reply.rno }&dnum=${dogContent.dnum }'">삭제</button>
						</td> 
						</c:if> 
					</tr>
				</c:forEach>
			</c:if>
    	</table>
    </div> 
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>