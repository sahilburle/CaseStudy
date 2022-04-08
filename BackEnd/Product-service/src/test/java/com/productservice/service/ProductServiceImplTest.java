package com.productservice.service;

import com.productservice.model.Product;
import com.productservice.repository.ProductRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {

    static List<Product> products;
    @Mock
    ProductRepo repo;
    @InjectMocks
    ProductServiceImpl service;

    @BeforeAll
    static void init() {
        products = new ArrayList<Product>();
        products.add(new Product("1", "Type1", "Product1", "Category1", null, null, null, 1200.0, "TestProduct1", null));
        products.add(new Product("2", "Type2", "Product2", "Category2", null, null, null, 1900.0, "TestProduct2", null));
        products.add(new Product("3", "Type3", "Product3", "Category2", null, null, null, 300.0, "TestProduct3", null));
        products.add(new Product("4", "Type4", "Product4", "Category3", null, null, null, 300.0, "TestProduct4", null));
    }

    @Test
    void addProducts() {

        Product newProduct = new Product("5", "newProduct", "Type3", "Category3", null, null, null, 300.0, "newTestProduct5", null);
        service.addProducts(newProduct);
        verify(repo,times(1)).save(newProduct);//used when there is no return type
    }

    @Test
    void getAllProducts() {

        when(repo.findAll()).thenReturn(products);
        assertEquals(products.size(), service.getAllProducts().size());
    }

    @Test
    void getProductById() {

        when(repo.findById("1")).thenReturn(products.stream().filter(product -> product.getProductId() == "1").findFirst());
        assertEquals("1", service.getProductById("1").get().getProductId());
    }

    @Test
    void updateProducts() {

        Product updateProduct = new Product("4", "UpdatedProduct4", "Type3", "Category3", null, null, null, 300.0, "UpdateTestProduct4", null);
        when(repo.save(updateProduct)).thenReturn(updateProduct);
        assertEquals(updateProduct, service.updateProducts(updateProduct));
    }

    @Test
    void deleteProductById() {

        String productId = "3";
        service.deleteProductById(productId);
        verify(repo,times(1)).deleteById(productId);
    }

    @Test
    @Order(3)
    @DisplayName("ProductByName")
    void productByName() {
        String name = "Product3";
        when(repo.findByproductName(name)).thenReturn(products.stream().filter(product -> product.getProductName() == name).findFirst()); //Mocking
        assertEquals(name, service.getProductByName(name).get().getProductName());
    }

    @Test
    @Order(4)
    @DisplayName("ProductByCategory")
    void productByCatgory() {
        String category = "Category2";
        when(repo.findBycategory(category)).thenReturn(products.stream().filter(product -> product.getCategory() == category).collect(Collectors.toList())); //Mocking
        assertEquals(2, service.getProductByCategory(category).size());
    }

    @Test
    @Order(5)
    @DisplayName("ProductByType")
    void productByType() {
        String type = "Type2";
        when(repo.findByproductType(type)).thenReturn(products.stream().filter(product -> product.getProductType() == type).collect(Collectors.toList())); //Mocking
        assertEquals(1, service.getProductByType(type).size());
    }
}