package com.lawencon.parkiran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.parkiran.dao.AccountDao;
import com.lawencon.parkiran.model.Account;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	@Qualifier("account_hibernate")
	AccountDao accountDao;

	@Override
	public void cekAccount(String username, String password) throws Exception {
		List<Account> listAcount = accountDao.checkAcount(username, password);
		if (listAcount.get(0).getUsername() == null) {
			throw new Exception();
		}
		
	}

}
