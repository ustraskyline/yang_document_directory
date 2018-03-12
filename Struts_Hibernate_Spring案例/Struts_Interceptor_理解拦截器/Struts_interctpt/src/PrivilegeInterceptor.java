import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Object user = actionContext.getSession().get("user");

		if (user != null) {
			System.out.println("Enter user != null");
			return invocation.invoke();
		} else {
			System.out.println("Enter user == null");
			actionContext.put("msg", "用户没有登录，请登录");
			return Action.LOGIN;
		}
	}
}
