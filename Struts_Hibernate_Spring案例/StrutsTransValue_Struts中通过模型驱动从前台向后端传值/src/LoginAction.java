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

	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		if ("itcast".equals(user.getUsername()) && "123".equals(user.getPassword())) {

			context.getSession().put("username", user.getUsername());
			context.getSession().put("password", user.getPassword());
			return SUCCESS;
		} else {
			context.getSession().put("error", "ÓÃ»§µÇÂ¼Ê§°Ü");
			return ERROR;
		}
	}
}
