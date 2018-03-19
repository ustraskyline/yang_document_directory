package example;

public class UserDaoImpl implements UserDao {

	@Override
	public void save() {
		System.out.println("save添加用户");
	}

	@Override
	public void update() {
		System.out.println("update更新用户");
	}

	@Override
	public void delete() {
		System.out.println("delete删除用户");
	}

	@Override
	public void find() {
		System.out.println("find查询用户");
	}

}
