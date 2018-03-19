import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;

// ʵ�� AsyncListener �ӿڵļ�������
public class AppAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent arg0) throws IOException {
		System.out.println("---> ����onComplete()����");
	}

	@Override
	public void onError(AsyncEvent arg0) throws IOException {
		System.out.println("---> ����onError()����");
	}

	@Override
	// AsyncContext���ڵ���request��startAsync()��������ܻ�ȡ����֮�������Ӽ�������
	// ֻ���ڵ��� startAsync()���������ܵ��ü������ġ�onStartAsync()����.
	public void onStartAsync(AsyncEvent arg0) throws IOException {
		System.out.println("---> ����onStartAsync()����");
	}

	@Override
	public void onTimeout(AsyncEvent asyncEvent) throws IOException {
		System.out.println("---> ����onTimeout()����");

		ServletResponse response = asyncEvent.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.println("����ʱ����");
	}
}
