package com.websitecontroller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class serviceImpl implements service{

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<?> viewProfiles() {
		
		return restTemplate.getForEntity("http://UserProfile-service/profile/getProfiles", List.class);
	}

	@Override
	public ResponseEntity<?> viewProducts() {
		
		return restTemplate.getForEntity("http://Product-service/products/allproducts", List.class);
	}
}
