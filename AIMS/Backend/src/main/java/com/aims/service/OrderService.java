package com.aims.service;

import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order createOrder(String cartId, DeliveryInfo deliveryInfo);
    Order getOrder(String orderId);
    Order updateStatusOrder(String orderId, String status);
    List<Order> getAllOrders();


}
