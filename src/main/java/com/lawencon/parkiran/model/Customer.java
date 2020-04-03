package com.lawencon.parkiran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cusId;
	
	@Column(nullable = false)
	private String tipeKendaraan;
	
	private String checkIn, checkOut;
	
	@Column(unique = true)
	private String platNo;
	
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public String getPlatNo() {
		return platNo;
	}
	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}
	public String getTipeKendaraan() {
		return tipeKendaraan;
	}
	public void setTipeKendaraan(String tipeKendaraan) {
		this.tipeKendaraan = tipeKendaraan;
	}
	
}
