package com.aims.service;

import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.entity.Order.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order createOrder(String cartId, DeliveryInfo deliveryInfo);
}
