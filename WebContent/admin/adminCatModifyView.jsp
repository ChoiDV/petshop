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
  <link href="${conPath }/css/doginsert.css" rel="stylesheet">
 <style>
 caption {
		font-weight: bold;
		font-size: 3em;
		color: white;
		margin-bottom: 30px;
   		text-shadow:-1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;
	}
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
 <link rel="stylesheet"
				href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
</head>
  <body>
	<jsp:include page="../main/header.jsp"/>
	<div id="form">
        <form action="${conPath }/CatModify.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="cnum" value="${catModifyContent.cnum }">
            <table id="input_form">
            	<caption>Cat Modify</caption>
                <tr>
                    <th>고양이 이름</th>
                    <td>
                        <input type="text" name="cname" required="required" value="${catModifyContent.cname }">
                    </td>
                </tr>
                <tr>
                    <th>고양이 성별</th>
                    <td>
                    	<c:if test="${catModifyContent.cgender eq 'F' }">
	                    	 <input type="radio" name="cgender" value="M">수컷
		                     <input type="radio" name="cgender" value="F" checked="checked">암컷
                    	</c:if>
                    	<c:if test="${catModifyContent.cgender eq 'M' }">
	                        <input type="radio" name="cgender" value="M" checked="checked">수컷
	                        <input type="radio" name="cgender" value="F">암컷
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>고양이 생년월일</th>
                    <td>
                        <input type="text" name="cbirth" id="datepicker" required="required" value="${catModifyContent.cbirth }">
                    </td>
                </tr>
                <tr>
                    <th>묘종</th>
                    <td>
                        <input type="text" name="cbreedno" list="cbreedname" required="required"  value="${catModifyContent.cbreedno }">
                        <datalist id="cbreedname" >
                            <c:forEach var="list" items="${breedlist }">
                                <option value="${list.cbreedno }">${list.cbreedname }</option>
                            </c:forEach>
                        </datalist>
                    </td>
                </tr>
                <tr>
                    <th>분양가</th>
                    <td>
                        <input type="number" name="cprice" required="required" value="${catModifyContent.cprice }">
                    </td>
                </tr>
                <tr>
                    <th>본문</th>
                    <td>
                        <textarea rows="13" cols="100" name="ccontent">${catModifyContent.ccontent }</textarea>
                    </td>
                </tr>
                <tr>
                    <th>대표사진</th>
                    <td>
                        <input type="file" name="cimage1">
                    </td>
                </tr>
                <tr>
                    <th>추가사진</th>
                    <td>
                        <input type="file" name="cimage2">
                        <input type="file" name="cimage3">
                        <input type="file" name="cimage4">
                        <input type="file" name="cimage5">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" id="submit">
                        <input type="submit" value="등록" class="btn">
                        <input type="button" value="뒤로가기" class="btn" onclick="location='${conPath}/adminPageCatListView.do?pageNum=${pageNum }'">
                    </td>
                </tr>
            </table>
        </form>
    </div>
		<jsp:include page="../main/footer.jsp"/>
		
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker(
				{				
					dateFormat : 'yy-mm-dd',
					changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
											'7월', '8월', '9월', '10월', '11월', '12월' ],
					showMonthAfterYear : true,
					yearSuffix : '년', // "2020년 3월"
					showOtherMonths : true,
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
					minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
					maxDate : 'y', // 현재 날짜이전까지만 표시
					yearRange : 'c-100:c+100', // 년도 선택 셀렉트 
				});
	});  
</script>
  </body>
</html>