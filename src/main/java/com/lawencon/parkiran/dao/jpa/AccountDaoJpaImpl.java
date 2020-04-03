package com.lawencon.parkiran.dao.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.AccountDao;
import com.lawencon.parkiran.dao.hibernate.CustomRevo;
import com.lawencon.parkiran.model.Account;

@Repository("account_jpa")
public class AccountDaoJpaImpl extends CustomRevo implements AccountDao{

	@Autowired
	AccountRevo accountRevo;
	
	@Override
	public List<Account> checkAcount(String username, String password) throws Exception {
		return accountRevo.findByUsernameAndPassword(username, password);
	}
	
	
}
