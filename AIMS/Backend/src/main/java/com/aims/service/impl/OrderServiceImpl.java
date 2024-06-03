package com.aims.service.impl;

import com.aims.entity.Cart.CartItem;
import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.entity.Order.Order;
import com.aims.entity.Order.OrderItem;
import com.aims.repository.OrderRepository;
import com.aims.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceImpl cartService;

    /**
     * 
     * Data coupling:
     * data coupling with OrderRepository and CartServiceImpl
     * Explanation: OrderServiceImpl depends on specific instances of OrderRepository and CartServiceImpl.
     * 
     * Functional Cohesion
     * 
     * @param orderRepository
     * @param cartService
     */
    public OrderServiceImpl(OrderRepository orderRepository, CartServiceImpl cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    /**
     * Control coupling:
     * Method call on cartService
     * Explanation: OrderServiceImpl directly calls getAllCartItems on CartServiceImpl.
     * 
     * Data coulping:
     * Creating OrderItem objects based on CartItem objects
     * Explanation: Transform the logic from CartItem to OrderItem is tightly integrated within the service.
     * 
     * Control coupling:
     *  Calculating the total amount including shipping fees
     *  Explanation: The service handles business logic for calculating the total amount.
     *  
     *  Data coupling:
     *  Setting up Order object with all necessary details
     *  Explanation: The service class directly manipulates the Order object, which is data coupling.
     *  
     *  Control coupling
     *  Method call on orderRepository
     *  Explanation: The service directly interacts with OrderRepository to save the order.
     *  
     *  Functional Cohesion: 
     *  Method handles creating an order
     *  
     *  Sequential Cohesion: 
     *  Steps to create an order are performed in sequence
     *  
     *  Communicational Cohesion: 
     *  Works with cart items and delivery info data
     * 
     */
    public Order createOrder(String cartId, DeliveryInfo deliveryInfo) {
        List<CartItem> cartItems = cartService.getAllCartItems(cartId);
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getSellPrice()))
                .toList();
        double totalAmount = orderItems.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum() + deliveryInfo.getShippingFees();
        Order order = new Order();
        order.setCartId(cartId);
        order.setListOrderItem(orderItems);
        order.setDeliveryInfo(deliveryInfo);
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }
}