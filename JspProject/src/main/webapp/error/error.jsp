<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!-- Throwable 객체인 exception을 사용할 것인지를 정함 -->

<% response.setStatus(HttpServletResponse.SC_OK); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외발생 </title>
</head>
<body>
요청 처리 과정에서 예외가 발생하였습니다.<br><br>
빠른 시간 내에 문제를 해결하도록 노력하겠습니다.<br><br>
<br></br>
에러타입 : <%=exception.getClass().getName() %><br><br>
에러 메시지 : <b><%=exception.getMessage() %></b>

</body>
</html>