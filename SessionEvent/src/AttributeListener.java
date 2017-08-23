import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class AttributeListener implements HttpSessionAttributeListener {
	public AttributeListener() {
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("===>Ìí¼ÓÊôÐÔ:" + arg0.getName() + "-->" + arg0.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("===>É¾³ýÊôÐÔ:" + arg0.getName() + "-->" + arg0.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("===>Ìæ»»ÊôÐÔ:" + arg0.getName() + "-->" + arg0.getValue());
	}

}
