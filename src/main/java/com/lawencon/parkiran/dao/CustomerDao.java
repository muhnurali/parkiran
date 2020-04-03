package com.lawencon.parkiran.dao;

import java.util.List;
import com.lawencon.parkiran.model.Customer;

public interface CustomerDao {
	abstract List<Customer> checkInReport() throws Exception;
	abstract List<Customer> checkOutReport() throws Exception;
	abstract List<Customer> customerCheckOut(Customer customer) throws Exception;
	abstract List<Customer> getAll() throws Exception;
	abstract void checkIn(Customer customer) throws Exception;
	abstract void checkOut(Customer customer) throws Exception;

}
