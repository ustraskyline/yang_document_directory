
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

// 使用AsyncContext时，实际的任务在另一个线程中处理
public class AsyncRequestProcesser implements Runnable {
	private AsyncContext asynccontext;
	private int millis;

	public AsyncRequestProcesser() {
	}

	public AsyncRequestProcesser(AsyncContext asyncCtx, int millis) {
		this.asynccontext = asyncCtx;
		this.millis = millis;
	}

	@Override
	public void run() {
		System.out.println("是否支持异步?" + asynccontext.getRequest().isAsyncSupported());
		longProcessing(millis);

		try {
			PrintWriter out = asynccontext.getResponse().getWriter();
			out.write("处理耗时: " + millis + "毫秒");
		} catch (IOException e) {
			e.printStackTrace();
		}

		asynccontext.complete();
	}

	private void longProcessing(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
