package com.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.productservice.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product, String>{

	Optional<Product> findByproductName(String productName);
	List<Product> findBycategory(String category);
	List<Product> findByproductType(String productType);
}
