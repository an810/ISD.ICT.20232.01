package com.aims.service.impl;

import com.aims.entity.cart.CartItem;
import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.order.Order;
import com.aims.entity.order.OrderItem;
import com.aims.exception.AIMSException;
import com.aims.exception.OrderNotFoundException;
import com.aims.repository.OrderRepository;
import com.aims.service.OrderService;
import com.aims.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceImpl cartService;

    public OrderServiceImpl(OrderRepository orderRepository, CartServiceImpl cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public Order createOrder(String cartId, DeliveryInfo deliveryInfo) {
        List<CartItem> cartItems = cartService.getAllCartItems(cartId);
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getSellPrice()))
                .toList();
        int totalAmount = orderItems.stream().mapToInt(item -> item.getQuantity() * item.getPrice()).sum();
        totalAmount += totalAmount * Constants.PERCENT_VAT/100  + deliveryInfo.getShippingFees();
        Order order = new Order();
        order.setCartId(cartId);
        order.setListOrderItem(orderItems);
        order.setDeliveryInfo(deliveryInfo);
        order.setTotalAmount(totalAmount);
        order.setStatus(Constants.ORDER_STATUS_PENDING);
        return orderRepository.save(order);
    }

    public Order getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            return order;
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

    public Order updateStatusOrder(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

}