import java.util.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class BindingListener implements HttpSessionBindingListener{

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("[" + new Date() + "] ==>" + arg0.getName() + " �󶨵� " + arg0.getSession().getId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("[" + new Date() + "] ==>" + arg0.getName() + " �� " + arg0.getSession().getId() + " �����");
	}

}
