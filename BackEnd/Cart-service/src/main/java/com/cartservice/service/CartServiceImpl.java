package com.cartservice.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cartservice.model.Cart;
import com.cartservice.model.Items;
import com.cartservice.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	//to get cart by ID
	@Override
	public Cart getcartById(String cartId) {
		
		return cartRepo.findBycartId(cartId);
	}

	//to update the cart
	@Override
	public Cart updateCart(Cart cart) {
		
		return cartRepo.save(cart);
	}

	//to get list of cart 
	@Override
	public List<Cart> getallCarts() {
		
		return cartRepo.findAll();
	}

	@Override
	public double cartTotal(Cart cart) {
		
		return cart.getTotalPrice();
	}

	@Override
	public void addCart(String userID,Cart cart) {
		
		cart.setCartId(userID);
		cartRepo.save(cart);				
	}
}