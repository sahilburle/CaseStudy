package com.cartservice.service;

import com.cartservice.model.Cart;
import com.cartservice.model.Items;
import com.cartservice.repository.CartRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CartServiceImplTest {

    static List<Cart> carts = new ArrayList<Cart>();
    static List<Items> items = new ArrayList<Items>();

    @Mock
    CartRepo repo;
    @InjectMocks
    CartServiceImpl service;

    @BeforeAll
    static void init() {

        items.add(new Items("Laptop", 50000, 1, "img1"));
        items.add(new Items("Mobile", 10000, 1, "img2"));
        items.add(new Items("Headphone", 5000, 2, "img3"));
        items.add(new Items("LED", 30000, 1, "img4"));

        carts.add(new Cart("1", 10000, items.subList(0, 2)));
        carts.add(new Cart("2", 50000, items.subList(1, 3)));
        carts.add(new Cart("3", 5000, items.subList(0, 3)));
    }

    @Test
    void getcartById() {

        String cartId = "1";

        when(repo.findBycartId(cartId)).thenReturn(carts.get(0));
        assertEquals(cartId, service.getcartById(cartId).getCartId());
    }

    @Test
    void updateCart() {

        Cart updateCart = new Cart("2", 30000, items.subList(1, 3));

        when(repo.save(updateCart)).thenReturn(updateCart);
        assertEquals(updateCart, service.updateCart(updateCart));
    }

    @Test
    void getallCarts() {

        when(repo.findAll()).thenReturn(carts);
        assertEquals(3, service.getallCarts().size());
    }

    @Test
    void cartTotal() {

        assertEquals(50000, service.cartTotal(carts.get(1)));
    }

    @Test
    void addCart() {

        Cart addCart = new Cart("1", 10000, items.subList(0, 1));
        service.addCart("1", addCart);
        verify(repo, times(1)).save(addCart);  //verification
    }
}