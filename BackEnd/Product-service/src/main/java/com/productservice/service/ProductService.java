package com.productservice.service;

import java.util.List;
import java.util.Optional;

import com.productservice.model.Product;

public interface ProductService {

	void addProducts(Product product);

	List<Product> getAllProducts();

	Optional<Product> getProductById(String productId);

	Product updateProducts(Product product);

	void deleteProductById(String productId);

	List<Product> getProductByCategory(String category);

	List<Product> getProductByType(String productType);

	Optional<Product> getProductByName(String name);
}
