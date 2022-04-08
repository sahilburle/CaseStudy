package com.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productservice.model.Product;
import com.productservice.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void addProducts(Product product) {
		
		productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getProductById(String productId) {
		
		return productRepo.findById(productId);
	}

	@Override
	public Product updateProducts(Product product) {
		
		return productRepo.save(product);
	}

	@Override
	public void deleteProductById(String productId) {
		productRepo.deleteById(productId);
	}

	@Override
	public List<Product> getProductByCategory(String category) {

		return productRepo.findBycategory(category);
	}

	@Override
	public List<Product> getProductByType(String productType) {

		return productRepo.findByproductType(productType);
	}

	@Override
	public Optional<Product> getProductByName(String name) {

		return productRepo.findByproductName(name);
	}
}
