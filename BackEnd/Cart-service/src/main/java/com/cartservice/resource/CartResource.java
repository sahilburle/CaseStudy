package com.cartservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartservice.model.Cart;
import com.cartservice.service.CartService;

@RestController
@EnableEurekaClient
@RequestMapping("mycart")
public class CartResource {

	@Autowired
	CartService cartService;
	
	@GetMapping("/cart/{cartId}")
	public Cart getCart(@PathVariable("cartId") String cartId) {
		
		return cartService.getcartById(cartId);
	}
	
	@GetMapping("/getAllCarts")
	public ResponseEntity<List<Cart>> getAllCarts() {
		
		return ResponseEntity.ok(cartService.getallCarts());
	}
	
	@PostMapping("/addCart/{userId}")
	public void addCart(@PathVariable("userId") String userId, @RequestBody Cart cart) {
		
		cartService.addCart(userId,cart);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
		
		return ResponseEntity.ok(cartService.updateCart(cart));
	}	
}
