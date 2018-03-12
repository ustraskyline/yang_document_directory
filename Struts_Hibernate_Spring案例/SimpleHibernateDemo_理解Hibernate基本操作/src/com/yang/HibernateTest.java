package com.yang;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class HibernateTest {

	public static void main(String[] args) {
		// addData();
		// modifyData();
		queryData();
		// delData();
		// queryByQuery();
		// queryByCriteria();
		// testKuaiZhao();
	}

	public static void addData() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();

		Customer c = new Customer();
		c.setName("yangjia");
		c.setAge(25);
		c.setCity("西安");
		c.setSex("男");

		session.save(c);
		t.commit();
		session.close();
		sessionFactory.close();
	}

	public static void modifyData() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();

		Customer c = new Customer();
		c.setId(2);
		c.setName("奥巴马");
		c.setAge(20);
		c.setCity("纽约");
		c.setSex("男");
		session.update(c);

		t.commit();
		session.close();
		sessionFactory.close();
	}

	public static void queryData() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();

		Customer c = (Customer) session.get(Customer.class, 1);
		c.setName("哈哈哈哈");
		
		//重新查询数据库，更新Hibernate快照和一级缓存
		session.refresh(c);

		t.commit();
		session.close();
		sessionFactory.close();
	}

	public static void delData() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();

		Customer c = (Customer) session.get(Customer.class, 2);
		session.delete(c);

		t.commit();
		session.close();
		sessionFactory.close();
	}

	// Query by Interface
	public static void queryByQuery() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();

		String hql = "from Customer";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();

		System.out.println("list=" + list);

		t.commit();
		session.close();
		sessionFactory.close();
	}

	// 条件查询通过Criteria完成
	public static void queryByCriteria() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		// 1. 通过Session得到Criteria对象，该对象就代表一个条件查询操作
		Criteria criteria = session.createCriteria(Customer.class);

		// 2. 通过Restrictions设置查询条件，返回Criterion对象，该对象代表一个查询条件
		Criterion criterion = Restrictions.eq("city", "成都");

		// 3. 向条件查询操作中添加查询条件
		criteria.add(criterion);

		// 4. 获取查询得到的结果
		List<Customer> list = criteria.list(); // 获取所有结果
		Customer c = (Customer) criteria.uniqueResult(); // 当此记录只有一条时，可用uniqueResult()获取

		System.out.println("所有结果为:" + list);
		System.out.println("唯一结果为:" + c);

		t.commit();
		session.close();
		sessionFactory.close();
	}

	public static void testKuaiZhao() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();

		Customer c = new Customer();
		c.setName("张三");
		c.setAge(28);
		c.setCity("重庆");
		c.setSex("男");

		session.save(c); // 写入到一级缓存和快照中的是"张三"
		c.setName("王六"); // 一级缓存中的更改为"王六", 快照区的仍为"张三"

		t.commit(); // 提交时会刷出一级缓存，将"王六"写入到数据库
		session.close();
		sessionFactory.close();
	}

}
