package com.lawencon.parkiran.dao.hibernate;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.parkiran.dao.CustomerDao;
import com.lawencon.parkiran.model.Customer;

@Repository("customer_hibernate")
public class CustomerDaoImpl extends CustomRevo implements CustomerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> checkInReport() throws Exception {
		Query q = em.createQuery("from Customer where checkOut is null");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> checkOutReport() throws Exception {
		Query q = em.createQuery("from Customer where checkOut is not null");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> customerCheckOut(Customer customer) throws Exception {
		Query q = em.createQuery("from Customer where platNo = :platNo");
		q.setParameter("platNo", customer.getPlatNo());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() throws Exception {
		Query q = em.createQuery("from Customer");
		return q.getResultList();
	}

	@Override
	public void checkIn(Customer customer) throws Exception {
		em.persist(customer);		
	}

	@Override
	public void checkOut(Customer customer) throws Exception {
		em.merge(customer);
	}

}
