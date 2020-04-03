package com.lawencon.parkiran.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.AccountDao;
import com.lawencon.parkiran.model.Account;

@Repository("account_hibernate")
public class AccountDaoImpl extends CustomRevo implements AccountDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> checkAcount(String username, String password) throws Exception {
		Query q = em.createQuery("from Account where username = :username and password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		return q.getResultList();
	}
}
