package com.lawencon.parkiran.dao;

import java.util.List;

import com.lawencon.parkiran.model.Account;

public interface AccountDao {
	abstract List<Account> checkAcount(String username, String password) throws Exception;
}
