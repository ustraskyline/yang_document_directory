package example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//MethodInterceptor: ����֪ͨ����Ŀ�귽��ִ��ǰ����ǿ��Ӧ������־���������
public class SAspect implements MethodInterceptor {

	//ȷ��Ŀ�귽��mi,���Ҹ���spring��Ŀ�귽��ִ��ǰ��ִ����Щ����
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("--->����ִ��ǰ");
		Object obj = mi.proceed();
		System.out.println("===>����ִ�к�");
		return obj;
	}

}
