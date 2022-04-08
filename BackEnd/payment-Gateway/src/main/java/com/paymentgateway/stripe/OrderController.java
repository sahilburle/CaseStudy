package com.paymentgateway.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/paymentStripe")
public class OrderController {

    @Autowired
    private OrderService orderService;

@PostMapping("/create-checkout-session")
public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckOutItemDto> checkOutItemDtoList)
throws StripeException {
    Session session = orderService.createSession(checkOutItemDtoList);
    StripeResponse stripeResponse = new StripeResponse(session.getId());
    return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
}

}
