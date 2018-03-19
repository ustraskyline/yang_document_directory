package com.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * 一级缓存是Session缓存，属于事务范围，由Hibernate进行管理;
 * 二级缓存是SessionFactory缓存，属于进程范围，由SessionFactory管理.
 * 
 * SessionFactory的缓存可分为两类：内置缓存、外置缓存
 * 内置缓存存储映射元数据和预定义SQL语句，映射元数据是映射文件数据的复制，预定义SQL是根据映射元数据推导出来的
 * 外置缓存存储的是数据库中的数据.
 * 
 * 二级缓存只能通过配置二级缓存插件来实现，常用的插件有EHCache/OpenSymphony OSCache/SwarmCache/JbossCache
 */

public class TestSecondLevelCache {
	public static void main(String[] args) {
		//以下程序Hibernate将只打印一个select语句
		
		//第一个session
		Session session1 = HibernateUtils.getSession();
		Transaction t1 = session1.beginTransaction();
		
		User user1 = (User) session1.get(User.class, 1);  //打印SQL语句
		User user2 = (User) session1.get(User.class, 1);  //从一级缓存读取
		
		System.out.println(user1 ==  user2);  //==>true, 因为user1和user2指向一级缓存中的同一数据
		t1.commit(); //清除一级缓存
		
		session1.close();
		
		//第二个session
		Session session2 = HibernateUtils.getSession();
		Transaction t2 = session2.beginTransaction();
	
		User user3 = (User) session2.get(User.class, 1);  //从二级缓存获取
		System.out.println(user1 == user3);  //==>false, 二级缓存装的是散装数据，获取后会重新new出一个新对象user3,
		
		User user4 = (User) session2.get(User.class, 1);  //从以及缓存获取
		System.out.println(user3 == user4);  //==>true, user3与user4指向一级缓存同一数据
		t2.commit();
		session2.close();
	}
}
