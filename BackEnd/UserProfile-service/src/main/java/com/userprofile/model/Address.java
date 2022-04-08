package com.userprofile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	int houseNumber;
	String streetName;
	String colonyName;
	String city;
	String state;
	int pincode;
}
