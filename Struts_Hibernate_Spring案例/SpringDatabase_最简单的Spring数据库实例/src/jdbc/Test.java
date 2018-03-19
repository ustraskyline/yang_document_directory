package jdbc;

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
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);
		
		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");
		
		//execute()����ִ��SQL���
		jdbcTemplate.execute("create table t_user("
				+ "userid int primary key auto_increment,"
				+ "username varchar(50),"
				+ "password varchar(32))");
	}
}
