package com.lawencon.parkiran.controller;
//by Muhammad Nur Ali

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.parkiran.model.Customer;
import com.lawencon.parkiran.service.CustomerService;

@RestController
public class HomeController extends BaseController {

	// admin : admin = YWRtaW46YWRtaW4=

	@Autowired
	CustomerService customerService;


	@PostMapping("/checkin")
	public ResponseEntity<String> checkin(@RequestBody String content,
			@RequestHeader("Authorization") String authorization) throws Exception {
		Customer customer = new Customer();
		String[] auth = super.authUser(authorization);
		try {
			customer = new ObjectMapper().readValue(content, Customer.class);
			customerService.checkIn(customer, auth[0], auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Parkir, Input platNo dan tipeKendaraan Dengan Benar",
					HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(customerService.checkIn(customer, auth[0], auth[1]), HttpStatus.ACCEPTED);
	}

	@PutMapping("/checkout")
	public ResponseEntity<String> checkout(@RequestBody String content,
			@RequestHeader("Authorization") String authorization) throws Exception {
		Customer customer = new Customer();
		String[] auth = super.authUser(authorization);
		try {
			customer = new ObjectMapper().readValue(content, Customer.class);
			customerService.checkOut(customer, auth[0], auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Parkir, Mohon Periksa platNo", HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(customerService.checkOut(customer, auth[0], auth[1]), HttpStatus.ACCEPTED);
	}


	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer(@RequestHeader("Authorization") String authorization) {
		List<Customer> listCustomers = new ArrayList<>();
		try {
			String[] auth = super.authUser(authorization);
			listCustomers = customerService.getAll(auth[0], auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>(listCustomers, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listCustomers, HttpStatus.OK);
	}


	@GetMapping("/reportcheckin")
	public ResponseEntity<?> getReportCheckIn(@RequestHeader("Authorization") String authorization) {
		List<Customer> listCustomers = new ArrayList<>();
		try {
			String[] auth = super.authUser(authorization);
			listCustomers = customerService.checkInReport(auth[0], auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Menampilkan Laporan Checkin", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listCustomers, HttpStatus.OK);
	}


	@GetMapping("/reportcheckout")
	public ResponseEntity<?> getReportCheckOut(@RequestHeader("Authorization") String authorization) {
		List<Customer> listCustomers = new ArrayList<>();
		try {
			String[] auth = super.authUser(authorization);
			listCustomers = customerService.checkOutReport(auth[0], auth[1]);
		} catch (Exception e) {
			return new ResponseEntity<>("Gagal Menampilkan Laporan Checkout", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listCustomers, HttpStatus.OK);
	}

}
