package com.aims.service;

import com.aims.entity.Cart.Cart;
import com.aims.entity.Cart.CartItem;
import com.aims.entity.Product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Cart getCart(String cartId);
    Cart addCartProduct(String cartId, String productId, int quantity);
    Cart removeCartProduct(String cartId, String productId);
    Cart clearCart(String cartId);
    List<Product> checkCartProducts(String cartId);
    List<CartItem> getAllCartItems(String cartId);
}
