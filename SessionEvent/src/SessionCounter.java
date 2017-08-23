import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener {
	private static int activeSession;

	public SessionCounter() {

	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		activeSession++;
		System.out.println("创建会话，id=" + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		activeSession--;
		System.out.println("销毁会话，id=" + event.getSession().getId());
	}

	public static int getActivedSessionCount() {
		return activeSession;
	}

}
