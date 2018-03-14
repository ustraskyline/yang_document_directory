package entity;

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

	@Test
	public void test2() {
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();

		User user = (User) session.get(User.class, 1);
		user.setUsername("yangjia");
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
