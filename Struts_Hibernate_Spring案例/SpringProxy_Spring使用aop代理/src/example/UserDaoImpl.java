package example;

public class UserDaoImpl implements UserDao {

	@Override
	public void save() {
		System.out.println("save����û�");
	}

	@Override
	public void update() {
		System.out.println("update�����û�");
	}

	@Override
	public void delete() {
		System.out.println("deleteɾ���û�");
	}

	@Override
	public void find() {
		System.out.println("find��ѯ�û�");
	}

}
