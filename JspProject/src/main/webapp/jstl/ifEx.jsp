<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<c:if test="${ 3 > 4 }">
이 내용은 화면에 나타나지 않음
</c:if>

<c:if test="${param.type eq 'guest' }">
홈 페이지 방문을 환영하는 바이나 아직 회원이 아니시므로<br>
빠른 시일내에 회원가입을 하시면 감사하겠습니다.<br>
즐거운 시간 보내세요.
</c:if>

<c:if test="${param.type eq 'member' }">
우리 회원님의 방문을 환영합니다.<br>
즐거운 시간을 보내시기 바라며<br>
보다 나은 서비스 제공을 위해 노력하겠습니다.
</c:if>

</body>
</html>