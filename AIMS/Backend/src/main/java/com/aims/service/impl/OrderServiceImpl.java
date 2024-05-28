package com.aims.service.impl;

import com.aims.entity.Cart.CartItem;
import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.entity.Order.Order;
import com.aims.entity.Order.OrderItem;
import com.aims.entity.Product.Product;
import com.aims.repository.OrderRepository;
import com.aims.service.OrderService;
import com.aims.utils.Constants;
import com.aims.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .map(cartItem -> new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice()))
                .toList();
        Order order = new Order();
        order.setCartId(cartId);
        order.setListOrderItem(orderItems);
        order.setDeliveryInfo(deliveryInfo);
        double total = orderItems.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
        order.setTotalAmount(total + deliveryInfo.getShippingFees());
        return orderRepository.save(order);
    }
}