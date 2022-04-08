package com.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.orderservice.model.Orders;

public interface OrderRepo extends MongoRepository<Orders, String>{
	
	List<Orders> findByCustomerId(String customerId);
	Orders findFirstByOrderByOrderIdDesc();

}
