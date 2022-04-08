package com.orderservice.resource;

import java.util.List;

import com.orderservice.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orderservice.model.Cart;
import com.orderservice.model.Orders;
import com.orderservice.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderResource {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/getAllorder")
	List<Orders> getAllOrders() {
		
		return orderService.getAllOrders();
	}

	@GetMapping("/customer/{customerId}")
	List<Orders> allOrdersOfCustomer(@PathVariable("customerId") String customerId)
	{
		return orderService.getOrderByCustomerId(customerId);
	}

	@GetMapping("/address/{customerId}")
	List<Address> allAddressOfCustomer(@PathVariable("customerId") String customerId)
	{
		return orderService.getAddressByCustomerId(customerId);
	}

	@GetMapping("/{orderId}")
	Orders orderByid(@PathVariable("orderId") String orderId)
	{
		return orderService.getOrderById(orderId).get();
	}

	@PutMapping("/statusChange/{orderId}")
	public String changeOrderstatus(@PathVariable String orderId, @RequestBody String status) {

		return orderService.changeStatus(status,orderId);
	}

	@PostMapping("/storeAddress")
	public void storeAddress(@RequestBody Address address) {
		orderService.storeAddress(address);
	}
	
	@PostMapping("/placeOrder")
	public void placeOrder(@RequestBody Cart cart) {
		
		orderService.placeOrder(cart);
	}
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public void deleteOrder(@PathVariable String orderId) {

		orderService.deleteOrder(orderId);
	}

	@GetMapping("/getAlladdress")
	List<Address> getAllAddress()
	{
		return orderService.getAllAddress();
	}
}

