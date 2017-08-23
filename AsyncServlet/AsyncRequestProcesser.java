
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

// ʹ��AsyncContextʱ��ʵ�ʵ���������һ���߳��д���
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
		System.out.println("�Ƿ�֧���첽?" + asynccontext.getRequest().isAsyncSupported());
		longProcessing(millis);

		try {
			PrintWriter out = asynccontext.getResponse().getWriter();
			out.write("�����ʱ: " + millis + "����");
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
