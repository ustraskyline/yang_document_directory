package entity;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class TestPessimisticLock {

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
	 *  test2()方法运行至途中，对数据的改变未提交->test3()运行至途中，未提交
	 * ->test2()运行完毕，提交->test3()运行完毕，提交==>test3()会覆盖test2()的修改
	 */
	@Test
	public void test2() {
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		// User user = (User) session.get(User.class, 1);
		// 添加悲观锁，本方法执行修改数据时，该条数据会被锁定，其他方法无法对
		// 此条数据进行操作，直到本事务提交后才被解锁
		User user = (User) session.get(User.class, 1, LockMode.UPGRADE);
		user.setUsername("yangjia");
		session.save(user);

		t.commit();
		session.close();
	}

	@Test
	public void test3() {
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		// 添加悲观锁 LockMode.UPGRADE
		User user = (User) session.get(User.class, 1, LockMode.UPGRADE);
		user.setAge(30);
		session.save(user);

		t.commit();
		session.close();
	}

}
