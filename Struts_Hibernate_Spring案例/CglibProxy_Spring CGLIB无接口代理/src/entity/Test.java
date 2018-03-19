package entity;

public class Test {

	public static void main(String[] args) {
		BookDao bookDao = BeanFactory.getBean();
		bookDao.save();
		bookDao.update();
		bookDao.delete();
		bookDao.find();
	}

}
