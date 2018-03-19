package com.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
	
	//模拟Spring的IoC, 在Test类中通过getBean()创建实例
	public static UserDao getBean() {
		//目标类对象
		final UserDao userDao = new UserDaoImpl();
		
		//切面类对象
		final UAspect uAspect = new UAspect();
		
		//使用代理类进行增强
		return (UserDao) Proxy.newProxyInstance(
				BeanFactory.class.getClassLoader(),   //当前BeanFactory的类加载器
				new Class[] {UserDao.class}, //要创建实例实现的接口
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//需要增强的方法, 在目标类执行前后，分别执行UAspect对象的before()和after()方法
						uAspect.before();
						//增强的是userDao对象的method参数指定的方法，方法参数为args
						//相当于调用userDao.method(args)
						Object obj = method.invoke(userDao, args);
						uAspect.after();
						
						return obj;
					}
					
				});
	}
}
