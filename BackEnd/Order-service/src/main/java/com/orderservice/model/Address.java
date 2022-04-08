package com.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
	
	String customerId;
	String fullName;
	String mobileNo;
	int flatNo;
	String city;
	int pincode;
	String state;

}