import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
		if ("tom".equals(user.getUsername()) && "12345".equals(user.getPassword())) {
			actionContext.getSession().put("user", user);
			return SUCCESS;
		} else {
			actionContext.put("msg", "用户名或密码不正确");
			return INPUT;
		}
	}
}
