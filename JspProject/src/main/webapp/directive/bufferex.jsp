<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer="1kb" autoFlush="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- 이 부분에서 4Kbyte 이상의 데이터가 발생됨 -->
<% for(int i = 0; i < 1000; i++) {%>
1234
<%} %>

</body>
</html>