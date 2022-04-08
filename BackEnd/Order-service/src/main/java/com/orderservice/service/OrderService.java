package com.orderservice.service;

import java.util.List;
import java.util.Optional;

import com.orderservice.model.Address;
import com.orderservice.model.Cart;
import com.orderservice.model.Orders;

public interface OrderService {
	
	List<Orders> getAllOrders();
	
	void placeOrder(Cart cart);
	
	String changeStatus(String orderId, String orderStatus);
	
	void deleteOrder(String orderId);
	
	List<Orders> getOrderByCustomerId(String customerId);
	
	void storeAddress(Address address);
	
	List<Address> getAddressByCustomerId(String customerId);
	
	List<Address> getAllAddress();
	
	Orders getOrderById();
	
	Optional<Orders> getOrderById(String orderId);
	
	void onlinePayment(Cart cart);

}
