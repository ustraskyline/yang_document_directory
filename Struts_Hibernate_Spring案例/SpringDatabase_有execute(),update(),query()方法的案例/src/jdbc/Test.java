package jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
/*
 * Spring��JdbcTemplate����Ϊ���ݿ�����࣬�����а������������ݿ�����ķ���
 * 
 * JdbcTemplate�̳��Գ�����JdbcAccessor��ͬʱʵ����JdbcOperations�ӿ�.
 * 
 * JdbcAccessor������Ϊ�����ṩ��һЩ�������ݿ�Ĺ������ԣ��磺
 *     DataSource:��ȡ���ݿ����ӣ���Ϊ�������ݿ���Դ�ı�׼�ӿ�
 *     SQLExceptionTranslator:��һ���ӿڣ�����ʹJdbcTemplate����SQLExceptionʱ��ί�иýӿڵ�ʵ����
 *     						  �������ص�ת�빤��.
 *     
 * JdbcOperations�ӿڶ�����JdbcTemplate���п���ʹ�õĲ�������
 */

public class Test {
	public static void main(String[] args) {
		findAllUser();
	}

	public static void createDatabase() {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");

		// execute()����ִ��SQL���
		jdbcTemplate.execute("create table t_user(" + "userid int primary key auto_increment," + "username varchar(50),"
				+ "password varchar(32))");
	}

	public static void addUser() {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) appContext.getBean("userDao");
		User user = new User();
		user.setUsername("jack");
		user.setPassword("1234");

		int flag = userDao.addUser(user);
		if (flag == 1) {
			System.out.println("����û��ɹ�");
		} else {
			System.out.println("����û�ʧ��");
		}
	}

	public static void updateUser() {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) appContext.getBean("userDao");
		User user = new User();
		user.setUserid(1);
		user.setUsername("tom");
		user.setPassword("1111");

		int flag = userDao.updateUser(user);
		if (flag == 1) {
			System.out.println("�޸��û��ɹ�");
		} else {
			System.out.println("�޸��û�ʧ��");
		}
	}

	public static void delUser() {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) appContext.getBean("userDao");
		int flag = userDao.deleteUserById(1);
		if (flag == 1) {
			System.out.println("ɾ���û��ɹ�");
		} else {
			System.out.println("ɾ���û�ʧ��");
		}
	}
	
	public static void findUserById() {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) appContext.getBean("userDao");
		User user = userDao.findUserById(1);
		
		System.out.println("user=" + user);
	}
	
	public static void findAllUser() {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);

		UserDao userDao = (UserDao) appContext.getBean("userDao");
		List<User> user = userDao.findAllUser();
		for(User u : user) {
			System.out.println(u);
		}
	}
}
