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
  <link href="${conPath }/css/join.css" rel="stylesheet">
 <style>
/*  	#idConfirmResult { */
/*  		font-size:0.7em; */
/*  		color:red; */
/*  	} */
	.result {
		width:30px;
	}
	#patternResult , #patternBirth {
		font-size: 0.7em;
	}
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		$('input[name="mid"]').keyup(function(){
 			var mid = $('input[name="mid"]').val();
 			$.ajax({
 				url : '${conPath}/idConfirm.do',
 				data : 'mid='+mid,
 				type : 'get',
 				dataType : 'html',
 				success : function(data){
 					$('#idConfirmResult').html(data);
 					if($('#idConfirmResult').html().trim() == '중복된 ID 입니다.' ){
 						$('#idConfirmResult').css('color','red');
 					}else{
 						$('#idConfirmResult').css('color','green');
 					}
 				}
 			});  // ajax함수
 		});  // keyup 함수
 		
 		$('input[name="mpwChk"]').keyup(function() { 
			var mpw = $('input[name="mpw"]').val();
			var mpwChk = $('input[name="mpwChk"]').val();
			if (mpw == mpwChk) {
				$('#pwChkResult').html('OK');
				$('#pwChkResult').css('color','green');
			} else {
				$('#pwChkResult').html('No');
				$('#pwChkResult').css('color','red');
			} // [A-Za-z](?=.*\d)[A-Za-z\d]{7,}$ 비밀번호 정규표현식 8자리 이상
			//  ^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$   8~16 자리 영문, 숫자, 특수문자
		}); // mpwChk.keyup 이벤트
		
		$('input[name="mpw"]').keyup(function(){  // 비밀번호 패턴 keyup 
			var patternPw = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
			var mpw = $('input[name="mpw"]').val().trim();
			if(patternPw.test(mpw)){
				$('#patternResult').html('안전');
				$('#patternResult').css('color','green');
			} else {
				$('#patternResult').html('위험(8~16 영문,숫자,특수문자)');
				$('#patternResult').css('color','red');  
			}
			
		});  // mpw  keyup 
		$('input[name="mbirth"]').keyup(function(){
			var mbirth = $('input[name="mbirth"]').val().trim();
			var patternBirth = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;
			if(patternBirth.test(mbirth)){
				$('#patternBirth').html('OK');
				$('#patternBirth').css('color','green');
			} else {
				$('#patternBirth').html('올바른 생년월일 6자리를 입력해주세요.');
				$('#patternBirth').css('color','red');
			}
		}); // 생년월일 6 자리 keyup 이벤트
		$('form').submit(function(){
				var idConfirmResult = $('#idConfirmResult').text().trim();
				var patternResult = $('#patternResult').text().trim();
				var pwChkResult = $('#pwChkResult').text().trim();
				var patternBirth = $('#patternBirth').text().trim();
				if(idConfirmResult != '사용가능한 ID 입니다.'){
					alert('사용가능한 ID로 가입해주세요.');
					$('#idConfirmResult').focus();
					return false;
				} else if(patternResult != '안전'){
					alert('비밀번호 형식을 지켜주세요');
					$('#patternResult').focus();
					return false;
				} else if(pwChkResult != 'OK'){
					alert('두 비밀번호가 다릅니다.');
					$('#pwChkResult').focus();
					return false;
				} else if(patternBirth != 'OK'){
					alert('생년월일 6자리를 입력해주세요.');
					$('#patternBirth').focus();
					return false;
				}
				
		});  // form submit 이벤트 
 	});  // ready
 	
 </script>  
</head>
  <body>
  	<jsp:include page="../main/header.jsp" />
  		   <div id="join_form">
        <form action="${conPath }/join.do" method="post">
            <table>
                <caption>회원가입</caption>
                <caption class="bigCap">CREATE YOUR ACCOUNT</caption>
                <tr>
                    <th colspan="2">아이디<b> *</b></th>
                    <td>
                        <input type="text" name="mid" required="required">
                        <div id="idConfirmResult"> &nbsp; </div>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">비밀번호<b> *</b></th>
                    <td>
                        <input type="password" name="mpw" placeholder="8~16자 영문, 숫자를 사용하세요" required="required">                    
                    </td>
                    <td>
                    	<div id="patternResult"> &nbsp; </div>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">비밀번호 재확인<b> *</b></th>
                    <td>
                        <input type="password" name="mpwChk" required="required">
                    </td>
                    <td>
                    	<span id="pwChkResult" > &nbsp; </span> 
                    </td>
                </tr>
                <tr>
                    <th colspan="2">이름<b> *</b></th>
                    <td>
                        <input type="text" name="mname" required="required">
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">전화번호<b> *</b></th>
                    <td>
                        <input type="text" name="mtel" required="required">
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">생년월일<b> *</b></th>
                    <td>
                        <input type="text" name="mbirth" placeholder=" 생년월일 6자리" required="required">
                    </td>
                    <td>
                    	 <div id="patternBirth"> &nbsp;</div>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">이메일<b> *</b></th>
                    <td>
                        <input type="email" name="memail" required="required">
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">주소<b> *</b></th>
                    <td>
                        <input type="text" name="maddress" required="required">
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">성별<b> *</b></th>
                    <td>
                        <input type="radio" name="mgender" value="M" class="btn">남성
                        <input type="radio" name="mgender" value="F" class="btn">여성
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="submit" value="회원가입" class="btn">
                        <input type="button" value="뒤로가기" onclick="location.href='history.back()'" class="btn">
                    </td>
                </tr>
            </table>
        </form>
    </div>
  		<jsp:include page="../main/footer.jsp" />
  </body>
  
</html>

