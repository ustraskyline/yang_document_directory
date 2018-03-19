package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
/*
 * Spring以JdbcTemplate类作为数据库访问类，该类中包含了所有数据库操作的方法
 * 
 * JdbcTemplate继承自抽象类JdbcAccessor，同时实现了JdbcOperations接口.
 * 
 * JdbcAccessor抽象类为子类提供了一些访问数据库的公共属性，如：
 *     DataSource:获取数据库连接，作为访问数据库资源的标准接口
 *     SQLExceptionTranslator:是一个接口，可以使JdbcTemplate处理SQLException时，委托该接口的实现类
 *     						  来完成相关的转译工作.
 *     
 * JdbcOperations接口定义了JdbcTemplate类中可以使用的操作集合
 */

public class Test {
	public static void main(String[] args) {
		String xmlPath = "jdbc/JdbcTemplateBeans.xml";
		ApplicationContext appContext = new ClassPathXmlApplicationContext(xmlPath);
		
		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");
		
		//execute()用于执行SQL语句
		jdbcTemplate.execute("create table t_user("
				+ "userid int primary key auto_increment,"
				+ "username varchar(50),"
				+ "password varchar(32))");
	}
}
