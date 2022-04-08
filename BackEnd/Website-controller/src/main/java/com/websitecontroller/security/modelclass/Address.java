package com.websitecontroller.security.modelclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private int houseNumber;
    private String street;
    private String colony;
    private String city;
    private String state;
    private int pinCode;
}
