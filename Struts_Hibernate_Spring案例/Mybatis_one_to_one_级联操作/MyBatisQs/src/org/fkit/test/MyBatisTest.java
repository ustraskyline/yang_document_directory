package org.fkit.test;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fkit.domain.Person;
import org.fkit.mapper.PersonMapper;

public class MyBatisTest {

	public static void main(String[] args) throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		// PersonMapper pm = session.getMapper(PersonMapper.class);
		// Person p = pm.selectPersonById(1);

		Person p = session.selectOne("org.fkit.mapper.PersonMapper.selectPersonById", 1);

		System.out.println(p);
		System.out.println(p.getCard());

		// 提交事务
		session.commit();
		// 关闭Session
		session.close();
	}
}
