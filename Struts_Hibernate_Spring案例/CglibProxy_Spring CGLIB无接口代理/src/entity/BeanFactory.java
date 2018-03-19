package entity;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/*
 * JDK动态代理必须实现一个或多个接口，若想代理没有实现接口的类，可以使用CGLIB.
 * 
 * CGLIB(Code Generation Library)通过为一个类创建子类，然后对子类进行增强，解决无接口代理问题
 */
public class BeanFactory {

	public static BookDao getBean() {
		// 目标类对象
		final BookDao bookDao = new BookDao();

		// 切面类对象
		final BAspect bAspect = new BAspect();

		// CGLIB代理的核心类Enhancer
		Enhancer enhancer = new Enhancer();
		
		//确定需要增强的类，CGLIB在运行时，生成指定对象的子类并增强之
		enhancer.setSuperclass(bookDao.getClass());
		//添加回调函数
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				//在目标方法执行前后，调用切面类的before()/after()方法进行增强
				bAspect.before();
				//相当于调用bookDao.method(args)
				Object obj = method.invoke(bookDao, args);
				bAspect.after();

				return obj;
			}
		});

		//创建代理类对象并返回
		BookDao bookDaoProxy = (BookDao) enhancer.create();
		return bookDaoProxy;
	}

}
