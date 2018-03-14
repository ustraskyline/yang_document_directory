package ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import di.UserService;

public class Test {

	public static void main(String[] args) {
		testSpringDI();
	}

	//最简单的Spring demo
	public static void testSpring() {
		String xmlPath = "applicationContext.xml";
		// 加载配置文件，初始化spring容器
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		// Spring Ioc: 此处没有new出一个UserDao接口的实现类对象，而是通过Spring容器获取的UserDao实例
		UserDao userDao = (UserDao) appContext.getBean("yangskyline");
		userDao.save();
	}

	//最简单的Spring依赖注入demo
	public static void testSpringDI() {
		String xmlPath = "applicationContext.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);
		UserService userService = (UserService) appContext.getBean("user_service");
		userService.addUser();
	}
}
