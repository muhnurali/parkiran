package com.lawencon.parkiran.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lawencon.parkiran.dao.AccountDao;
import com.lawencon.parkiran.dao.CustomerDao;
import com.lawencon.parkiran.model.Account;
import com.lawencon.parkiran.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	@Qualifier("customer_jpa")
	CustomerDao customerDao;
	
	@Autowired
	@Qualifier("account_jpa")
	AccountDao accountDao;

	@Override
	public List<Customer> checkInReport(String username, String password) throws Exception {
		cekAccount(username, password);
		return customerDao.checkInReport();
	}

	@Override
	public List<Customer> checkOutReport(String username, String password) throws Exception {
		cekAccount(username, password);
		return customerDao.checkOutReport();
	}

	@Override
	public String checkIn(Customer customer, String username, String password) throws Exception {
		cekAccount(username, password);	
		char noPol1 = customer.getPlatNo().charAt(0);
		int noPol2 = Integer.parseInt(customer.getPlatNo().substring(1, 5));
		String noPol3 = customer.getPlatNo().substring(5, 8);
		if (customer.getTipeKendaraan().equalsIgnoreCase("mobil") || customer.getTipeKendaraan().equalsIgnoreCase("motor")) {
			if (noPol1 == 'B' || noPol1 == 'b') {
				if (noPol2 >= 0000 && noPol2 <= 9999) {
					if (!noPol3.matches("[0-9]")) {
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
						LocalDateTime now = LocalDateTime.now();
						customer.setCheckIn(dtf.format(now));
						customerDao.checkIn(customer);
					}
				}
			} else {
				return "Hanya Plat B Yang Boleh Parkir";
			}
		}else {
			return "Tipe Kendaraan Yang Diperbolehkan Hanya Mobil dan Motor";
		}	
		return "Berhasil Check In";
	}

	@Override
	public String checkOut(Customer customer, String username, String password) throws Exception {	
		cekAccount(username, password);
		List<Customer> temp = customerDao.customerCheckOut(customer);
		if (temp.get(0).getPlatNo() != null) {
			customer.setCusId(temp.get(0).getCusId());
			customer.setCheckIn(temp.get(0).getCheckIn());
			customer.setPlatNo(temp.get(0).getPlatNo());
			customer.setTipeKendaraan(temp.get(0).getTipeKendaraan());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			customer.setCheckOut(dtf.format(now));
			customerDao.checkOut(customer);
		}else {
			return "No Polisi Tidak Ditemukan";
		}
		return "Berhasil Check Out";
	}

	@Override
	public List<Customer> getAll(String username, String password) throws Exception {
		cekAccount(username, password);
		return customerDao.getAll();
	}
	
	public void cekAccount(String username, String password) throws Exception {
		List<Account> listAcount = accountDao.checkAcount(username, password);
		if (listAcount.get(0).getUsername() == null) {
			throw new Exception();
		}
	}


}
