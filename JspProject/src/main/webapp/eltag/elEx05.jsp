<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파라미터</title>
</head>
<body>
<h3>파라미터 처리</h3>
<br><br>

<form action="elEx05.jsp" method="post">
  이름 : <input type="text" name="name" value="${param['name']}">
  		<input type="submit" value="확인">
<p>
  이름은 : ${param.name }입니다.
</p>

</form>
</body>
</html>