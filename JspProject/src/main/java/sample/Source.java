package sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Source")
public class Source extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Source Start .....");
		
        /*		
        페이지 이동
        메소드 호출을 통해서 페이지를 이동할 수 있는 방법
        1. forward 방식으로 이동
		  - url : url이 바뀌지 않는다.
		  요청객체와 응답객체 : 유지한다.
		  속도 : 빠르다.
		  소속 : 요청객체
        */
		//RequestDispatcher view = request.getRequestDispatcher("Destination");
		//view.forward(request, response);
		
		/*		
		 2. redirect 방식으로 이동
         - url : url이 바뀐다.
		  요청객체와 응답객체 : 유지되지 않는다.
		  속도 : 느리다.
		  소속 : 응답객체
       */
		response.sendRedirect("Destination");
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
