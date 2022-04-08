package com.orderservice.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "order")
public class Orders {

	@Id
	String orderId;
	LocalDate orderDate;
	String customerId;
	double amountPaid;
	String modeOfPayment;
	String orderStatus;
	int quantity;
	List<Items> itemsList;
}
