<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, memberone.*" %>    
    
<!-- 동 이름으로 매개변수로 받아서 데이터베이스에서 데이터를 가져와서 보여줌 -->    
<jsp:useBean id="dao" class="memberone.StudentDAO" />

<%
request.setCharacterEncoding("utf-8");

String check = request.getParameter("check");
String dong = request.getParameter("dong");

Vector<ZipcodeVO> zipcodeList = dao.zipcodeRead(dong);

int totalList = zipcodeList.size();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<link href="../css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body bgcolor="#CEF6E3">
<div align="center">
<b>우편번호 찾기</b>
<form action="zipCheck.jsp" name="zipForm" method="post">
<table>
  <tr>
    <td>동 이름 : <input name="dong" type="text">
    <input type="button" value="검색" onClick="dongCheck()">
    </td>
  </tr>
</table>
<input type="hidden" name="check" value="n">
</form>
<table>

<%
  if(check.equals("n")) {
%>
	<%
	if(zipcodeList.isEmpty()) {
	%>  
	<tr><td align="center"><br> 검색된 결과가 없습니다. </td></tr>
	<%}else {%>
	<tr><td align="center"><br>
	※ 검색 후, 아래 우편번호를 클릭하면 자동으로 입력됩니다. 
	</td></tr>
	<%
	for(int i=0; i < totalList; i++) {
		ZipcodeVO vo = zipcodeList.elementAt(i);
		
		String tempZipcode = vo.getZipcode();
		String tempSido = vo.getSido();
		String tempGugun = vo.getGugun();
		String tempDong = vo.getDong();
		String tempRi = vo.getRi();
		String tempBunji = vo.getBunji();
		
		if(tempRi == null) tempRi="";
		if(tempBunji == null) tempBunji="";
	%>
	<tr><td>
	<a href="javascript:sendAddress('<%=tempZipcode%>','<%=tempSido%>','<%=tempGugun%>','<%=tempDong%>','<%=tempRi%>','<%=tempBunji%>')">
	<%=tempZipcode%>&nbsp;<%=tempSido%>&nbsp;<%=tempGugun%>&nbsp;<%=tempDong%>&nbsp;<%=tempRi%>&nbsp;<%=tempBunji%>&nbsp;
	</a><br>
	<%
		}// end for
	}// end else
  }//end if
	%>

  <tr>
    <td><a href="#" onClick="javascript:self.close()">닫기</a></td>
  </tr>
</table>

</div>
</body>
</html>