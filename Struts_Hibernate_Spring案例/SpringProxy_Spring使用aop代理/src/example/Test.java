package example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		String xmlpath = "applicationContext.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlpath);
		
		//��Spring�л��ָ��������UserDao�����䷽���Ѿ�����ǿ
		UserDao userDao = (UserDao) appContext.getBean("userDaoProxy");
		
		userDao.save();
		userDao.update();
		userDao.delete();
		userDao.find();
	}
}
