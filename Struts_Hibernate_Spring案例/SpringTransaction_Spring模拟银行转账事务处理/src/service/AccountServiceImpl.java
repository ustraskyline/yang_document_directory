package service;

import dao.AccountDao;

public class AccountServiceImpl implements AccountService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String outUser, String inUser, int money) {
		this.accountDao.out(outUser, money);
//		int i = 1 / 0;  //模拟系统故障
		this.accountDao.in(inUser, money);
		
	}

}
