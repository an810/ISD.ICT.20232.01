package com.aims.service;

import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.order.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order createOrder(String cartId, DeliveryInfo deliveryInfo);
}
