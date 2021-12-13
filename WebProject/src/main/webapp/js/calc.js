// 빈 객체 생성
var calc = {};

calc.x = 0;
calc.y = 0;

// 멤버변수 값을 변경하기 위한 메소드
calc.setValue = function(p1,p2) {
	this.x = p1;
	this.y = p2;
}

// 덧셈
calc.plus = function() {
	return this.x + this.y;
}

// 뺄셈
calc.minus = function() {
	return this.x - this.y;
}

// 덧셈과 뺄셈의 결과를 출력하는 메소드
calc.result = function() {
	
	// 메소드 안에서 객체에 존재하는 다른 메소드를 호출할 경우 this라는 예약어를 사용해야 함
	var v1 = this.plus();
	var v2 = this.minus();
	
	document.write("<p> 덧셈 : "+v1+"</p>");
	document.write("<p> 뺄셈 : "+v2+"</p>");
}