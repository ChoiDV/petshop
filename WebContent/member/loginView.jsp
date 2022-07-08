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
	#login_form {
		margin-top:100px;	
	}
	
</style>
<link href="${conPath }/css/login.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
 		$('.bt').hover(function(){
 			$(this).css('backgroundColor','saddlebrown'); 
 			$(this).css('color','white');		
 		},function(){
 			$(this).css('background-color','#ffd1dc');
 			$(this).css('color','black');
 		});
 	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	 <div id="content_form">
        <div id="login_form">
            <form action="${conPath }/login.do" method="post"> 
                <table>
                    <caption><a href="${conPath }/main.do">Companion</a></caption>
                    <tr>               
                        <td>
                           	<input type="text" name="mid" required="required" placeholder=" 아이디" value="${mid }${param.mid}">
                        </td>
                    </tr>
                    <tr>
                      
                        <td>
                            <input type="password" name="mpw" required="required" placeholder=" 비밀번호">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="idpwsearch"><a href="${conPath }/searchIdView.do">아이디</a>/<a href="${conPath }/searchPwView.do">비밀번호</a> 찾기</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" class="bt" value="로그인" />
                            <input type="button" class="bt" value="회원가입"  onclick="location.href='${conPath}/joinAgree.do'"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <jsp:include page="../main/footer.jsp" />
</body>
</html>

