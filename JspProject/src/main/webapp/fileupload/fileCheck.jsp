<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

String name = request.getParameter("name");
String subject = request.getParameter("subject");
String fileName1 = request.getParameter("fileName1");
String fileName2 = request.getParameter("fileName2");
String origfileName1 = request.getParameter("origfileName1");
String origfileName2 = request.getParameter("origfileName2");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 확인 및 다운로드</title>
</head>
<body>
<div align="center">
올린사람 : <%=name %><br>
제목 : <%=subject %><br>
파일명1 : <a href="/JspProject/upload/<%=fileName1%>">
<%=origfileName1 %></a><br>
파일명2 : <a href="/JspProject/upload/<%=fileName2%>">
<%=origfileName2 %></a><br>
</div>

</body>
</html>