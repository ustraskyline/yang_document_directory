package example;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller("userAction")
public class UserAction {
	
	private UserService userService;

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void save() {
		this.userService.save();
		System.out.println("userAction...save...");
	}
	
}
