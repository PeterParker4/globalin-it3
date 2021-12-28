<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Dog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

#content_main {
	height: 300px;
}

#content_left {
	height: 300px;
	float: left;
}

#content_right {
	height: 340px;
	float: left;
}

#commandList {
	text-align: center;
}

#desc {
	height: 170px;
	background-color: skyblue;
}

</style>
</head>
<body>
<section>
<h2>${dog.kind }의 상세 정보</h2>
	<section id="content_main">
		<section id="content_left">
			<img alt="" src="image/${dog.image }">
		</section>
		<section id="content_right">
			<b>품종 : </b>${dog.kind }<br>
			<b>가격 : </b>${dog.price }<br>
			<b>신장 : </b>${dog.weight }<br>
			<b>체중 : </b>${dog.height }<br>
			<b>원산지 : </b>${dog.country }<br>
			<p id="desc">
			 <b>내용 : </b>${dog.content }<br>
			</p>
		</section>
		<div style="clear:both"></div>
		<nav id="commandList">
			<a href="dogList.dog">쇼핑 계속하기</a>
			<a href="dogCartADD.dog?id=${dog.id }">장바구니 담기</a>
		</nav>
	</section>
</section>
</body>
</html>