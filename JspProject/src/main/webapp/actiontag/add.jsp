<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="actiontag.Customer" %>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="customer" class="actiontag.Customer" scope="page" />
<jsp:setProperty property="*" name="customer"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
  <ul>
    <li>이름 : <jsp:getProperty property="name" name="customer"/></li>
    <li>이메일 : <jsp:getProperty property="email" name="customer"/></li>
    <li>전화번호 : <jsp:getProperty property="phone" name="customer"/></li>
  </ul>


</body>
</html>