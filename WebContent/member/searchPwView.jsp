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
  <link href="${conPath }/css/login.css" rel="stylesheet">
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
	 <div id="content_form">
        <div id="login_form">
            <form action="${conPath }/searchPw.do" method="post">
                <table>
                    <caption><a href="../main/main.jsp">비밀번호 찾기</a></caption>
                     <tr>               
                        <td>
                            <input type="text" name="mid" required="required" placeholder=" 찾을 아이디를  입력하세요." value="${mid }">
                        </td>
                    </tr>
                    <tr>               
                        <td>
                            <input type="text" name="mname" required="required" placeholder=" 회원가입시 등록한 이름을 입력하세요.">
                        </td>
                    </tr>
                    <tr>
                      
                        <td>
                            <input type="text" name="mbirth" required="required" placeholder=" 회원가입시 입력한 생년월일6자리">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="idpwsearch"><a href="${conPath }/loginView.do">로그인</a></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" class="bt" value="찾기" />
                            <input type="button" class="bt" value="아이디 찾기" onclick="location.href=${conPath }/searchIdView.do"/>
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