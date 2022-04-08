package com.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.apache.naming.java.javaURLContextFactory;
import org.bouncycastle.crypto.RawAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation.FromBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderservice.model.Address;
import com.orderservice.model.Cart;
import com.orderservice.model.Orders;
import com.orderservice.model.Product;
import com.orderservice.repository.AddressRepo;
import com.orderservice.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<Orders> getAllOrders() {
		
		return orderRepo.findAll();
	}

	public Cart updateCart(Cart cart) {
		
		double totalValue = 0;
		
		//taking product price from product-service and calulating total cartvalue
		for(int i = 0; i < cart.getItemsList().size(); i++)
		{
			//only names of an item 1 by 1
			String itemName = cart.getItemsList().get(i).getProductName();
			
			//taking obj from product-service by item name
			Product items = restTemplate.getForObject("http://product-service/products/single/" + itemName , Product.class);
						
			//cart ke item ki price set kr rahe product object from product-service
			cart.getItemsList().get(i).setPrice(items.getPrice());
			
			totalValue += cart.getItemsList().get(i).getPrice() * cart.getItemsList().get(i).getQuantity();
			
		}
		cart.setTotalPrice(totalValue);
		
		return cart;
	}
	
	@Override
	public void placeOrder(Cart cart) {

		cart = updateCart(cart);
		
		Orders newOrder = new Orders();
		
		newOrder.setCustomerId(cart.getCartId());
		newOrder.setAmountPaid(cart.getTotalPrice());
		newOrder.setModeOfPayment(null);
		newOrder.setOrderDate(java.time.LocalDate.now());
		newOrder.setQuantity(cart.getItemsList().size());
		newOrder.setOrderStatus("Placed");
		newOrder.setItemsList(cart.getItemsList());
		
		//adding to database the complete order
		orderRepo.save(newOrder);
	}

	@Override
	public String changeStatus(String orderId, String orderStatus) {
		
		//order current status is updated and returning updated status
		orderRepo.findById(orderId).get().setOrderStatus(orderStatus);
		return orderRepo.findById(orderId).get().getOrderStatus();
	}

	@Override
	public void deleteOrder(String orderId) {
		
		orderRepo.deleteById(orderId);
	}

	@Override
	public List<Orders> getOrderByCustomerId(String customerId) {
		
		return orderRepo.findByCustomerId(customerId);
	}

	@Override
	public void storeAddress(Address address) {

		addressRepo.save(address);	
	}

	@Override
	public List<Address> getAddressByCustomerId(String customerId) {
		
		return addressRepo.findByCustomerId(customerId);

	}

	@Override
	public List<Address> getAllAddress() {
		
		return addressRepo.findAll();
	}

	@Override
	public Optional<Orders> getOrderById(String orderId) {
		
		return orderRepo.findById(orderId) ;
	}

	@Override
	public void onlinePayment(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Orders getOrderById() {
		// TODO Auto-generated method stub
		return null;
	}
}
