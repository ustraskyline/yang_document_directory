import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// ServletContext�ر�ʱע���̳߳�
		ThreadPoolExecutor executor = (ThreadPoolExecutor) arg0.getServletContext().getAttribute("executor");
		executor.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// ServletContext��ʼ��ʱ�½�һ���̳߳أ�������ΪServletContext���������
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
		arg0.getServletContext().setAttribute("executor", executor);
	}

}
