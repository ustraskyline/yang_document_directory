import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.AccountService;

public abstract class Test {

	public static void main(String[] args) {
		String xmlPath = "applicationContext.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);
		
		AccountService accountService = (AccountService) appContext.getBean("accountServiceProxy");
		accountService.transfer("jack", "ROSE", 100);
		System.out.println("Transfer Succeed!");
	}

}
