package com.productservice.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "product")
public class Product {

	@Id
	String productId;
	String productType;
	String productName;
	String category;
	Map<Integer, Double> rating;
	Map<Integer, String> review;
	String image;
	double price;
	String description;
	Map<String, String> specification;		
}
