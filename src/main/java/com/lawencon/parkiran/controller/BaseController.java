package com.lawencon.parkiran.controller;

import java.util.Base64;

public abstract class BaseController {
	
	public String[] authUser(String authorization) {
		byte[] decodedBytes = Base64.getDecoder().decode(authorization);
		String decodedString = new String(decodedBytes);
		String[] authArr = decodedString.split(":");
		return authArr;
	}
}
