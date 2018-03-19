
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncLongRunningServlet
 */
@WebServlet(urlPatterns = "/AsyncLongRunningServlet", asyncSupported = true)
public class AsyncLongRunningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		long startTime = System.currentTimeMillis();

		System.out.println("AsyncLoongRunningServlet ����: Name=" + Thread.currentThread().getName() + " ID= "
				+ Thread.currentThread().getId());

		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

		int millis = 5000;

		// =====================================================================================
		// ������������ startAsync() ���������첽Servlet, ���� AsyncContext �ӿ�ʵ��
		AsyncContext ayncCtx = request.startAsync();
		ayncCtx.addListener(new AppAsyncListener()); // ���첽 Servlet ���ü�����
		ayncCtx.setTimeout(9000); // ���ó�ʱ,��9000ms��û�д����������Զ��Ͽ����Ӳ�����AsyncListener �������е� onTimeout() ����

		// ��ServletContext�����л���̳߳ز������̣߳�ʵ��������AsyncRequestProcesser�д���
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		executor.execute(new AsyncRequestProcesser(ayncCtx, millis));
		// =====================================================================================

		long endTime = System.currentTimeMillis();

		System.out.println("AsyncLoongRunningServlet������  Name=" + Thread.currentThread().getName() + " ID="
				+ Thread.currentThread().getId() + " ��ʱ=" + (endTime - startTime) + "����");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
