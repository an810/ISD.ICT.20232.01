package com.aims.service;

import com.aims.entity.cart.Cart;
import com.aims.entity.cart.CartItem;
import com.aims.entity.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Cart getCart(String cartId);
    Cart addCartProduct(String cartId, String productId, int quantity);
    Cart removeCartProduct(String cartId, String productId);
    Cart clearCart(String cartId);
    List<CartItem> getAllCartItems(String cartId);
}
