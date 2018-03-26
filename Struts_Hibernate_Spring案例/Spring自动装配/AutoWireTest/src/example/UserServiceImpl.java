package example;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save() {
		this.userDao.save();
		System.out.println("userservice...save...");
	}

}
