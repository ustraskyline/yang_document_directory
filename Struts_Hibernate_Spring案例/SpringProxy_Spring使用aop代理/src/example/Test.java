package example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		String xmlpath = "applicationContext.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlpath);
		
		//从Spring中获得指向代理类的UserDao对象，其方法已经被增强
		UserDao userDao = (UserDao) appContext.getBean("userDaoProxy");
		
		userDao.save();
		userDao.update();
		userDao.delete();
		userDao.find();
	}
}
