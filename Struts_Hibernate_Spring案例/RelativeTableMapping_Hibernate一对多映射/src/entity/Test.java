package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		Customer c = new Customer();
		c.setName("杨佳");
		
		Order o1 = new Order();
		o1.setAddress("成都");
		o1.setPrice(1000d);
		
		Order o2 = new Order();
		o2.setAddress("广元");
		o2.setPrice(2555d);
		
		
		
		c.getOrders().add(o1);
		c.getOrders().add(o2);
		
//		o1.setCustomer(c);
//		o2.setCustomer(c);
		
		session.save(c);
		session.save(o1);
		session.save(o2);
		
		t.commit();
		session.close();
		sessionFactory.close();

	}

}
