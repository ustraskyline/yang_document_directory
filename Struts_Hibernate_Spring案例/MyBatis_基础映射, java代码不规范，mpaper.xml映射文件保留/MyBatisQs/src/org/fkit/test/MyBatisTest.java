package org.fkit.test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fkit.domain.Student;
import org.fkit.domain.User;

public class MyBatisTest {

	public static void main(String[] args) throws Exception {
		// 读取mybatis-config.xml文件
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		// 初始化mybatis，创建SqlSessionFactory类的实例
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 创建Session实例
		SqlSession session = sqlSessionFactory.openSession();

		List<Student> list = session.selectList("org.fkit.mapper.UserMapper.selectStudent");
		System.out.println("length=" + list.size());

		for (Student stu : list) {
			System.out.println(stu);
		}

		// 提交事务
		session.commit();
		// 关闭Session
		session.close();
	}
}
