package sample;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// ������
    public LifeCycle() {
    	System.out.println("LifeCycle�� ������");
    }

    // LifeCycle�� �ʱ�ȭ �۾��� ���
    // Servlet ��ü ������ �� �ѹ��� �����
	public void init() throws ServletException {
		System.out.println("init() ȣ���......");
	}

	// Ŭ���̾�Ʈ�� ��û�� ���� ������ ȣ���
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() ȣ���.....");
	}

	// ����(��ü�� �Ҹ�� �� ȣ���)
	public void destroy() {
		System.out.println("destroy() ȣ���.....");
	}

}
