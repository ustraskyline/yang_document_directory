package example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//MethodInterceptor: 环绕通知，在目标方法执行前后增强，应用于日志、事务管理
public class SAspect implements MethodInterceptor {

	//确定目标方法mi,并且告诉spring在目标方法执行前后执行哪些方法
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("--->方法执行前");
		Object obj = mi.proceed();
		System.out.println("===>方法执行后");
		return obj;
	}

}
