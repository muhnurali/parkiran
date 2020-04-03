package com.lawencon.parkiran.dao.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.CustomerDao;
import com.lawencon.parkiran.dao.hibernate.CustomRevo;
import com.lawencon.parkiran.model.Customer;

@Repository("customer_jpa")
public class CustomerDaoJpaImpl extends CustomRevo implements CustomerDao{

	@Autowired
	CustomerRevo customerRevo;
	
	@Override
	public List<Customer> checkInReport() throws Exception {
		return customerRevo.findByCheckOutNull();
	}

	@Override
	public List<Customer> checkOutReport() throws Exception {
		return customerRevo.findByCheckOutNotNull();
	}

	@Override
	public List<Customer> customerCheckOut(Customer customer) throws Exception {
		return customerRevo.findByPlatNo(customer.getPlatNo());
	}

	@Override
	public List<Customer> getAll() throws Exception {
		return customerRevo.findAll();
	}


	@Override
	public void checkIn(Customer customer) throws Exception {
		customerRevo.save(customer);		
	}

	@Override
	public void checkOut(Customer customer) throws Exception {
		customerRevo.save(customer);
		
	}
}
