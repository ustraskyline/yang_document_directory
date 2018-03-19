import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class AttributeListener implements HttpSessionAttributeListener {
	public AttributeListener() {
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("===>�������:" + arg0.getName() + "-->" + arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("===>ɾ������:" + arg0.getName() + "-->" + arg0.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("===>�滻����:" + arg0.getName() + "-->" + arg0.getValue());
	}

}
