package com.aims.controller;

import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.order.Order;
import com.aims.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class PlaceOrderController {

    private final OrderService orderService;

    public PlaceOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public Order placeOrder(@RequestParam String cartId, @RequestBody DeliveryInfo deliveryInfo) {
        return orderService.createOrder(cartId, deliveryInfo);
    }
}