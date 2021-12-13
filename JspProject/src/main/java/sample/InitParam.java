package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/InitParam")
public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String company;
	private String manager;
	private String tel;
	private String email;
	
	public void init() throws ServletException {
		
		// ServletContext의 초기 파라미터 값 읽어옴
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");

		// ServletConfig의 초기 파라미터 값 읽어옴
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}

	protected void processRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			out.println("<html>");
			out.println("<body>");
			out.println("<ul>");
			out.println("<li>회 사 명 : " + company + "</li>");
			out.println("<li>담 당 자 : " + manager + "</li>");
			out.println("<li>전화번호 : " + tel + "</li>");
			out.println("<li>이 메 일 : " + email + "</li>");
			out.println("</ul>");
			out.println("</body>");
			out.println("</html>");
		}finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
