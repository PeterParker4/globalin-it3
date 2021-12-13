<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% response.setStatus(HttpServletResponse.SC_OK); %>    
<!-- 
  HttpServletResponse.SC_OK : 응답 코드 200
   - 응답코드를 200으로 지정하지 않으면 웹 브라우저에서는 404 또는 500 응답코드가 전송되며,
     이 경우 웹 브라우저 자체적으로 404 또는 500 에러일 때 보여주는 화면을 출력함
	 사용하는 컨테이너에 따라서 200으로 지정하지 않아도 되는 경우도 있음
 -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 에러 발생</title>
</head>
<body>
<b>서비스 처리 과정에서 에러가 발생하였습니다.</b><br>
<b>빠른 시간안에 문제를 해결하도록 노력하겠습니다.</b><br>
</body>
</html>