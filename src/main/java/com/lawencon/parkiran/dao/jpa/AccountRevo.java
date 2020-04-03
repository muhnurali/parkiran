package com.lawencon.parkiran.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.model.Account;
@Repository
public interface AccountRevo extends JpaRepository<Account, Integer>{
	abstract List<Account> findByUsernameAndPassword(String username, String password);
}
