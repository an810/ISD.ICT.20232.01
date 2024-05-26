package com.aims.controller;

import com.aims.entity.Cart.Cart;
import com.aims.entity.Order.Order;
import com.aims.exception.ProductNotAvailableException;
import com.aims.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class PlaceOrderController {

    private final OrderService orderService;

    public PlaceOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.processOrder(order);
    }

    @GetMapping("/check-cart")
    public ResponseEntity<String> checkCart(@RequestBody Cart cart) {
        try {
            cart.checkAvailabilityOfProduct();
        } catch (ProductNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("All products are available", HttpStatus.OK);
    }

}