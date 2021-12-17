<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<%@ page import="java.util.HashMap, java.util.Date" %>
<%
	HashMap<String, Object> mapData = new HashMap<String, Object>();
	mapData.put("name", "홍길동");
	mapData.put("today", new Date());
%>

<c:set var="intArray" value="<%=new int[]{1,2,3,4,5} %>"/>
<c:set var="map" value="<%=mapData %>"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h4>int형 배열</h4>
<c:forEach var="i" items="${intArray }" begin="2" end="4">
  [${i}]
</c:forEach>

<h4>Map</h4>
<c:forEach var="i" items="${map }">
  ${i.key }=${i.value }<br><br>
</c:forEach>

<h4>1부터 100까지의 홀수의 합계</h4>
<c:set var="sum" value="0"/> <!-- sum = 0 -->
<c:forEach var="i" begin="1" end="100" step="2">
  <c:set var="sum" value="${sum+i}"/>
</c:forEach>
결과 : ${sum}

<h4>구구단 : 4단</h4>
<ul>
  <c:forEach var="i" begin="1" end="9">
    <li>4 x ${i} = ${4*i}</li>
  </c:forEach>
</ul>

<h4>3의 배수를 출력하는 프로그램</h4>
  <c:forEach var="i" begin="3" end="15" step="3">
    ${i}&nbsp;
  </c:forEach>

<h4>7 팩토리얼을 구하여 출력하시오</h4>
<c:set var="fac" value="1"/>
  <c:forEach var="i" begin="1" end="7">
    <c:set var="fac" value="${fac*i}"/>
  </c:forEach>
  결과 : ${fac}
</body>
</html>