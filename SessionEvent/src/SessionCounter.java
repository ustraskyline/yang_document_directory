import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
	private static int activeSession;

	public SessionCounter() {

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		activeSession++;
		System.out.println("�����Ự��id=" + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		activeSession--;
		System.out.println("���ٻỰ��id=" + event.getSession().getId());
	}

	public static int getActivedSessionCount() {
		return activeSession;
	}

}
