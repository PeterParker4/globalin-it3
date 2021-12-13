package test2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListenerImpl implements ServletContextListener {

	@Override // ������ �� �޸𸮿��� ����(�� ���ø����̼� ����)
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("�� ���ø����̼� ����.....");
	}

	@Override // �� ���ø����̼� �ʱ�ȭ
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("�� ���ø����̼� �ʱ�ȭ.....");
	}

}
