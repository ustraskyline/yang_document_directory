import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;

// 实现 AsyncListener 接口的监听器类
public class AppAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent arg0) throws IOException {
		System.out.println("---> 调用onComplete()方法");
	}

	@Override
	public void onError(AsyncEvent arg0) throws IOException {
		System.out.println("---> 调用onError()方法");
	}

	@Override
	// AsyncContext是在调用request的startAsync()方法后才能获取到，之后才能添加监听器，
	// 只有在调用 startAsync()后，容器才能调用监听器的　onStartAsync()方法.
	public void onStartAsync(AsyncEvent arg0) throws IOException {
		System.out.println("---> 调用onStartAsync()方法");
	}

	@Override
	public void onTimeout(AsyncEvent asyncEvent) throws IOException {
		System.out.println("---> 调用onTimeout()方法");

		ServletResponse response = asyncEvent.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.println("处理超时错误");
	}
}
