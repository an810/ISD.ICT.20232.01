package com.aims.service.impl;

import com.aims.entity.Cart.Cart;
import com.aims.entity.Invoice;
import com.aims.entity.Order.Order;
import com.aims.repository.OrderRepository;
import com.aims.service.OrderService;
import com.aims.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order processOrder(Order order) {
        try {
            Cart.getCart().checkAvailabilityOfProduct();
//            Utils.processDeliveryInfo(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Invoice.createInvoice(order);
//        Utils.calculateShippingFee(order);
        orderRepository.save(order);

        return order;
    }
}