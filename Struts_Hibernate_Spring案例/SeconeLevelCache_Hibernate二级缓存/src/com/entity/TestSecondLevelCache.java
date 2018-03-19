package com.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * һ��������Session���棬��������Χ����Hibernate���й���;
 * ����������SessionFactory���棬���ڽ��̷�Χ����SessionFactory����.
 * 
 * SessionFactory�Ļ���ɷ�Ϊ���ࣺ���û��桢���û���
 * ���û���洢ӳ��Ԫ���ݺ�Ԥ����SQL��䣬ӳ��Ԫ������ӳ���ļ����ݵĸ��ƣ�Ԥ����SQL�Ǹ���ӳ��Ԫ�����Ƶ�������
 * ���û���洢�������ݿ��е�����.
 * 
 * ��������ֻ��ͨ�����ö�����������ʵ�֣����õĲ����EHCache/OpenSymphony OSCache/SwarmCache/JbossCache
 */

public class TestSecondLevelCache {
	public static void main(String[] args) {
		//���³���Hibernate��ֻ��ӡһ��select���
		
		//��һ��session
		Session session1 = HibernateUtils.getSession();
		Transaction t1 = session1.beginTransaction();
		
		User user1 = (User) session1.get(User.class, 1);  //��ӡSQL���
		User user2 = (User) session1.get(User.class, 1);  //��һ�������ȡ
		
		System.out.println(user1 ==  user2);  //==>true, ��Ϊuser1��user2ָ��һ�������е�ͬһ����
		t1.commit(); //���һ������
		
		session1.close();
		
		//�ڶ���session
		Session session2 = HibernateUtils.getSession();
		Transaction t2 = session2.beginTransaction();
	
		User user3 = (User) session2.get(User.class, 1);  //�Ӷ��������ȡ
		System.out.println(user1 == user3);  //==>false, ��������װ����ɢװ���ݣ���ȡ�������new��һ���¶���user3,
		
		User user4 = (User) session2.get(User.class, 1);  //���Լ������ȡ
		System.out.println(user3 == user4);  //==>true, user3��user4ָ��һ������ͬһ����
		t2.commit();
		session2.close();
	}
}
