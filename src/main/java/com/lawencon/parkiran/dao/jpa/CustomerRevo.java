package com.lawencon.parkiran.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.model.Customer;

@Repository
public interface CustomerRevo extends JpaRepository<Customer, Integer> {
	
	abstract List<Customer> findByCheckOutNull();
	abstract List<Customer> findByCheckOutNotNull();
	abstract List<Customer> findByPlatNo(String platNo);

}
