<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="memberone.*" %>
<jsp:useBean id="dao" class="memberone.StudentDAO" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<meta charset="UTF-8" http-equiv="Refresh" content="3; url=login.jsp">
<%
	String id = (String)session.getAttribute("loginID");
	String pass = request.getParameter("pass");
	int check = dao.deleteMember(id, pass);
	
	if(check == 1) {
		session.invalidate();
%>
<body>
<div align="center">
<font size="5" face="굴림체">
<b>회원정보가 삭제되었습니다.</b><br><br>
안녕히 가세요. 적립된 포인트는 환불되지 않습니다.<br><br>
3초 후에 로그인 페이지로 이동합니다.
</font>
<% }else { %>
<script type="text/javascript">
alert("비밀번호가 틀렸습니다.");
history.go(-1);
</script>
<%} %>
</div>
</body>
</html>