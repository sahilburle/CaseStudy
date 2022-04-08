package com.cartservice.service;

import java.util.List;

import com.cartservice.model.Cart;

public interface CartService {
	
	public Cart getcartById(String cartId);
	
	public Cart updateCart(Cart cart);
	
	public List<Cart> getallCarts();
	
	public double cartTotal(Cart cart);
	
	public void addCart(String userId,Cart cart);
}
