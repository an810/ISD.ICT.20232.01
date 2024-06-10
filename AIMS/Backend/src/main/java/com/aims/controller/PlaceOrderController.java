package com.aims.controller;

import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.order.Order;
import com.aims.entity.response.AIMSResponse;
import com.aims.service.OrderService;
import com.aims.utils.Constants;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AIMSResponse<Order>> placeOrder(@RequestParam String cartId, @RequestBody DeliveryInfo deliveryInfo) {
        Order order = orderService.createOrder(cartId, deliveryInfo);
        AIMSResponse<Order> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Place order successfully", order);
        return ResponseEntity.ok(response);
    }



}