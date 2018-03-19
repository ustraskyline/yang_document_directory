package entity;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/*
 * JDK��̬�������ʵ��һ�������ӿڣ��������û��ʵ�ֽӿڵ��࣬����ʹ��CGLIB.
 * 
 * CGLIB(Code Generation Library)ͨ��Ϊһ���ഴ�����࣬Ȼ������������ǿ������޽ӿڴ�������
 */
public class BeanFactory {

	public static BookDao getBean() {
		// Ŀ�������
		final BookDao bookDao = new BookDao();

		// ���������
		final BAspect bAspect = new BAspect();

		// CGLIB����ĺ�����Enhancer
		Enhancer enhancer = new Enhancer();
		
		//ȷ����Ҫ��ǿ���࣬CGLIB������ʱ������ָ����������ಢ��ǿ֮
		enhancer.setSuperclass(bookDao.getClass());
		//��ӻص�����
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				//��Ŀ�귽��ִ��ǰ�󣬵����������before()/after()����������ǿ
				bAspect.before();
				//�൱�ڵ���bookDao.method(args)
				Object obj = method.invoke(bookDao, args);
				bAspect.after();

				return obj;
			}
		});

		//������������󲢷���
		BookDao bookDaoProxy = (BookDao) enhancer.create();
		return bookDaoProxy;
	}

}
