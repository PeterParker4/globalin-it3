<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
  private int numOne = 0;
  
  public void jspInit() {// 최초 호출시에만 작동함
	  System.out.println("jspInit() 메소드 호출");
  }

  public void jspDestroy() {// tomcat에 종료될 때 호출
	  System.out.println("jspDestroy() 메소드 호출");
  }
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
  <%
    // 연산 처리
    int numTwo = 0;
    numOne++; // 새로고침 할때마다 증가(_jspService() 호출) // 재정의가 불가능
    numTwo++; // 새로고침 할때마다 초기화(jspInit() 호출) // 재정의 가능
  %>
  <ul>
    <li>Number One : <%=numOne %></li>
    <li>Number Two : <%=numTwo %></li>
  </ul>

</body>
</html>