package com.aims.controller;


import com.aims.entity.order.Order;
import com.aims.service.OrderService;
import com.aims.utils.Constants;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/update-status/approve/{orderId}")
    public Order approveOrder(@PathVariable String orderId) {
        return orderService.updateStatusOrder(orderId, Constants.ORDER_STATUS_PROCESSING);
    }

    @PutMapping("/update-status/cancel/{orderId}")
    public Order cancelOrder(@PathVariable String orderId) {
        return orderService.updateStatusOrder(orderId, Constants.ORDER_STATUS_CANCELLED);
    }

    @PutMapping("/update-status/reject/{orderId}")
    public Order rejectOrder(@PathVariable String orderId) {
        return orderService.updateStatusOrder(orderId, Constants.ORDER_STATUS_REJECTED);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

}
