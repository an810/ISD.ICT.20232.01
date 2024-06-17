package com.aims.service.impl;

import com.aims.entity.cart.CartItem;
import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.order.Order;
import com.aims.entity.order.OrderItem;
import com.aims.entity.product.Product;
import com.aims.exception.AIMSException;
import com.aims.exception.OrderNotFoundException;
import com.aims.repository.OrderRepository;
import com.aims.repository.ProductRepository;
import com.aims.service.OrderService;
import com.aims.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartServiceImpl cartService;

    public OrderServiceImpl(OrderRepository orderRepository, CartServiceImpl cartService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @Override
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

    @Override
    public Order getOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            return order;
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

    @Override
    public Order updateStatusOrder(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getStatus().equals(Constants.ORDER_STATUS_PENDING)) {
            order.setStatus(status);
            if (status.equals(Constants.ORDER_STATUS_PROCESSING)) {
                // update the quantity of product by subtracting the quantity of product in the order
                order.getListOrderItem().forEach(orderItem -> {
                    updateQuantityProduct(orderItem.getProduct().getId(), orderItem.getQuantity());
                });
            }
            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private void updateQuantityProduct(String productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        } else {
            throw new AIMSException("Product not found");
        }
    }
}