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
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/update-status/approve/{orderId}")
    public ResponseEntity<AIMSResponse<Order>> approveOrder(@PathVariable String orderId) {
        Order order =  orderService.updateStatusOrder(orderId, Constants.ORDER_STATUS_PROCESSING);
        AIMSResponse<Order> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Approve order successfully", order);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-status/cancel/{orderId}")
    public ResponseEntity<AIMSResponse<Order>>  cancelOrder(@PathVariable String orderId) {
        Order order = orderService.updateStatusOrder(orderId, Constants.ORDER_STATUS_CANCELLED);
        AIMSResponse<Order> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Cancel order successfully", order);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-status/reject/{orderId}")
    public ResponseEntity<AIMSResponse<Order>>  rejectOrder(@PathVariable String orderId) {
        Order order = orderService.updateStatusOrder(orderId, Constants.ORDER_STATUS_REJECTED);
        AIMSResponse<Order> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Reject order successfully", order);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<AIMSResponse<Order>>  getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        AIMSResponse<Order> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get order successfully", order);
        return ResponseEntity.ok(response);
    }

}
