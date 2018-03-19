package com.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
	
	//ģ��Spring��IoC, ��Test����ͨ��getBean()����ʵ��
	public static UserDao getBean() {
		//Ŀ�������
		final UserDao userDao = new UserDaoImpl();
		
		//���������
		final UAspect uAspect = new UAspect();
		
		//ʹ�ô����������ǿ
		return (UserDao) Proxy.newProxyInstance(
				BeanFactory.class.getClassLoader(),   //��ǰBeanFactory���������
				new Class[] {UserDao.class}, //Ҫ����ʵ��ʵ�ֵĽӿ�
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//��Ҫ��ǿ�ķ���, ��Ŀ����ִ��ǰ�󣬷ֱ�ִ��UAspect�����before()��after()����
						uAspect.before();
						//��ǿ����userDao�����method����ָ���ķ�������������Ϊargs
						//�൱�ڵ���userDao.method(args)
						Object obj = method.invoke(userDao, args);
						uAspect.after();
						
						return obj;
					}
					
				});
	}
}
