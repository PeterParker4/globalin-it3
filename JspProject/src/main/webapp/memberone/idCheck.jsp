<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="dao" class="memberone.StudentDAO" />
<%
	String id = request.getParameter("id");
	boolean check = dao.idCheck(id);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
<link href="../css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body bgcolor="#CEF6E3">
<br>
<div align="center">
<b><%=id %></b>
<%
if(check) {
	out.println("는 이미 존재하는 아이디입니다.<br><br>");
}else {
	out.println("는 사용 가능한 아이디입니다.<br><br>");
}
%>
<a href="#" onClick="javascript:self.close()">닫기</a>
</div>

</body>
</html>