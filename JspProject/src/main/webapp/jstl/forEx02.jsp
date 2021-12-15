<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList, jstl.MemberVO"%>

<%
MemberVO vo1 = new MemberVO("손오공", "son@naver.com", 28);
MemberVO vo2 = new MemberVO("배지터", "bae@naver.com", 30);
MemberVO vo3 = new MemberVO("피콜로", "picol@naver.com", 100);

ArrayList<MemberVO> memberList = 
new ArrayList<MemberVO>();

memberList.add(vo1);
memberList.add(vo2);
memberList.add(vo3);

request.setAttribute("memberList", memberList);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h3>회원정보</h3>
<table border="1" width="450">
  <tr>
    <td width="50">번호</td>
    <td width="100">이름</td>
    <td width="200">메일</td>
    <td width="100">나이</td>
  </tr>

  <c:forEach var="member" items="${memberList }" varStatus="num">
  <tr>
    <td align="center">${num.count }</td>
    <td align="center">${member.name }</td>
    <td align="center">${member.email }</td>
    <td align="center">${member.age }</td>
  </tr>
  </c:forEach>

for(i=1; i < 5; i++) {
arr[i] = 6;

</table>
</body>
</html>