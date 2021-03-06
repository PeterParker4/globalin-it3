<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="vo" class="jstl.MemberVO" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!--
 target : 프로퍼티 값을 설정할 대상 객체를 지정함
 property : 설정할 프로퍼티 이름을 지정함
 value : 프로퍼티 값을 지정함
 -->
<c:set target="${vo }" property="name" value="홍길동" />
<c:set target="${vo }" property="email">
hong@naver.com
</c:set>

<c:set var="age" value="30" />
<c:set target="${vo }" property="age" value="${age}" />

<h3>회원 정보</h3>

<ul>
<li>이름 : ${vo.name }</li>
<li>메일 : ${vo.email }</li>
<li>나이 : ${vo.age }</li>
</ul>

</body>
</html>