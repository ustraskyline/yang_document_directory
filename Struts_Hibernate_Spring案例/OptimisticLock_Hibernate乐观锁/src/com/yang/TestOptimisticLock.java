package com.yang;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class TestOptimisticLock {
	// @Test
	public void test1() {
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		User user = (User) session.get(User.class, 1);
		System.out.println("id=" + user.getId());
		System.out.println("name=" + user.getUsername());
		System.out.println("age=" + user.getAge());

		t.commit();
		session.close();
	}

	/*
	 * >>>第二类丢失更新
	 * 
	 * test2()方法运行至途中，对数据的改变未提交->test3()运行至途中，未提交
	 * ->test2()运行完毕，提交->test3()运行完毕，提交==>test3()会覆盖test2()的修改
	 * 
	 * 乐观锁以id和version一起来决定一个更新对象，更新前先select查出当前的id和verion, 在执行update语句时，会将id = ? and version = ?
	 * 作为where子句的条件，更新成功后将version加1.
	 * 
	 * 更新时若找不到匹配记录，Hibernate会抛出StaleObjectStateException
	 * 
	 * 在实际应用中，应该捕获抛出的异常，然后通过自动回滚或通知用户的方式进行相应处理.
	 */
	@Test
	public void test2() {
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		User user = (User) session.get(User.class, 1);
		user.setUsername("John");
		session.save(user);

		t.commit();
		session.close();
	}

	@Test
	public void test3() {
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		User user = (User) session.get(User.class, 1);
		user.setAge(30);
		session.save(user);

		t.commit();
		session.close();
	}
}
