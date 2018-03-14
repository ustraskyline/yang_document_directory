package ioc;

public class UserDaoImpl implements UserDao {

	@Override
	public void save() {
		System.out.println("Spring: Hello UserDao");
	}

}
