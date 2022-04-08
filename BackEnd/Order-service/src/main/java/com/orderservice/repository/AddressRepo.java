package com.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.orderservice.model.Address;

public interface AddressRepo extends MongoRepository<Address, String>{

	List<Address> findByCustomerId(String customerId);
}
