<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
  forEach : 배열과 컬렉션 데이터를 루프로 작업할 때 사용함
  			변수로 제공되는 프로퍼티는 다음과 같다.
  index : 루프 실행에서 현재 인덱스
  count : 루프 실행 횟수
  begin : 시작
  end : 마지막
  step : 증가
  first : 현재 실행이 첫번째이면 true
  last : 현재 실행이 마지막이면 true
  current : 컬렉션 중 현재 루프에서 사용할 객체
  
  var,
  items : 반복 처리할 데이터를 의미함
  varStatus : 루프 상태를 저장할 EL 변수이름
 -->
<%
  String[] movieList={"어벤져스","스파이더맨","아이언맨","캡틴아메리카"};
  request.setAttribute("movieList", movieList);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
마블 시리즈 : 
<ul>
  <c:forEach var="movie" items="${movieList }">
    <li>${movie }</li>
  </c:forEach>
</ul>

</body>
</html>