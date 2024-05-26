package com.aims.service;

import com.aims.entity.Cart.Cart;

public interface CartService {
    public Cart getCart(String cartId);
    public Cart addCartProduct(String cartId, String productId, int quantity);
    public Cart removeCartProduct(String cartId, String productId);
    public Cart clearCart(String cartId);

}
