package com.entity;

public class Test {

	public static void main(String[] args) {
		UserDao userDao = BeanFactory.getBean();
		userDao.save();
		userDao.update();
		userDao.delete();
		userDao.find();
	}

}
