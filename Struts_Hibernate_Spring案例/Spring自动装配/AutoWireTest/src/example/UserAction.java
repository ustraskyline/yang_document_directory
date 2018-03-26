package example;


public class UserAction {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void save() {
		this.userService.save();
		System.out.println("userAction...save...");
	}
	
}
