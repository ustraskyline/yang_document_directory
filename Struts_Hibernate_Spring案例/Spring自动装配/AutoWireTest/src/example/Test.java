package example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		String xmlpath = "applicationContext.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlpath);
		
		UserAction userAction = (UserAction) appContext.getBean("userAction");
		
		System.out.println("===>" + userAction);
		userAction.save();
	}
}
