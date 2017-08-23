
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

		System.out.println("AsyncLoongRunningServlet 启动: Name=" + Thread.currentThread().getName() + " ID= "
				+ Thread.currentThread().getId());

		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

		int millis = 5000;

		// =====================================================================================
		// 调用请求对象的 startAsync() 方法启动异步Servlet, 返回 AsyncContext 接口实例
		AsyncContext ayncCtx = request.startAsync();
		ayncCtx.addListener(new AppAsyncListener()); // 给异步 Servlet 设置监听器
		ayncCtx.setTimeout(9000); // 设置超时,在9000ms内没有处理，容器会自动断开连接并调用AsyncListener 监听器中的 onTimeout() 方法

		// 从ServletContext属性中获得线程池并启动线程，实际任务在AsyncRequestProcesser中处理
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		executor.execute(new AsyncRequestProcesser(ayncCtx, millis));
		// =====================================================================================

		long endTime = System.currentTimeMillis();

		System.out.println("AsyncLoongRunningServlet结束：  Name=" + Thread.currentThread().getName() + " ID="
				+ Thread.currentThread().getId() + " 耗时=" + (endTime - startTime) + "毫秒");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
