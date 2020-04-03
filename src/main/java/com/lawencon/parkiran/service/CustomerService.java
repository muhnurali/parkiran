package com.lawencon.parkiran.service;

import java.util.List;
import com.lawencon.parkiran.model.Customer;

public interface CustomerService {
	abstract List<Customer> checkInReport(String username, String password) throws Exception;
	abstract List<Customer> checkOutReport(String username, String password) throws Exception;
	abstract String checkIn(Customer customer, String username, String password) throws Exception;
	abstract String checkOut(Customer customer,String username, String password) throws Exception;
	abstract List<Customer> getAll(String username, String password) throws Exception;
}
