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
 <link rel="stylesheet"
				href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
</head>
  <body>
  	<jsp:include page="../main/header.jsp"/>
  	<jsp:include page="../admin/adminPageMenu.jsp"/>
	<div id="form">
        <form action="${conPath }/DogInsert.do" method="post" enctype="multipart/form-data">
            <table id="input_form">
            	<caption>Dog Register</caption>
                <tr>
                    <th>강아지 이름</th>
                    <td>
                        <input type="text" name="dname" required="required">
                    </td>
                </tr>
                <tr>
                    <th>강아지 성별</th>
                    <td>
                        <input type="radio" name="dgender" value="M">수컷
                        <input type="radio" name="dgender" value="F">암컷
                    </td>
                </tr>
                <tr>
                    <th>강아지 생년월일</th>
                    <td>
                        <input type="text" name="dbirth" id="datepicker" required="required">
                    </td>
                </tr>
                <tr>
                    <th>견종</th>
                    <td>
                        <input type="text" name="dbreedno" list="dbreedname" required="required">
                        <datalist id="dbreedname">
                            <c:forEach var="list" items="${breedlist }">
                                <option value="${list.dbreedno }">${list.dbreedname }</option>
                            </c:forEach>
                        </datalist>
                    </td>
                </tr>
                <tr>
                    <th>분양가</th>
                    <td>
                        <input type="number" name="dprice" required="required">
                    </td>
                </tr>
                <tr>
                    <th>본문</th>
                    <td>
                        <textarea rows="13" cols="100" name="dcontent"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>대표사진</th>
                    <td>
                        <input type="file" name="dimage1" required="required">
                    </td>
                </tr>
                <tr>
                    <th>추가사진</th>
                    <td>
                        <input type="file" name="dimage2">
                        <input type="file" name="dimage3">
                        <input type="file" name="dimage4">
                        <input type="file" name="dimage5">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" id="submit">
                        <input type="submit" value="등록" class="btn">
                        <input type="button" value="뒤로가기" class="btn" onclick="location='history.back()'">
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