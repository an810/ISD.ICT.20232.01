package com.aims.service.impl;

import com.aims.entity.cart.Cart;
import com.aims.entity.cart.CartItem;
import com.aims.entity.product.Product;
import com.aims.exception.ProductNotAvailableException;
import com.aims.repository.CartRepository;
import com.aims.repository.ProductRepository;
import com.aims.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart getCart(String cartId) {
        // Ensure the cart is properly initialized
        return cartRepository.findById(cartId).orElse(new Cart(cartId, new ArrayList<>(), 0));
    }

    @Override
    public Cart addCartProduct(String cartId, String productId, int quantity) {
        Cart cart = getCart(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotAvailableException("Product not found"));

        if (cart.getListCartItem() == null) {
            cart.setListCartItem(new ArrayList<>());
        }

        Optional<CartItem> existingItem = cart.getListCartItem().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            cart.getListCartItem().add(new CartItem(product, quantity));
        }

        cart.setTotalPrice(cart.getListCartItem().stream()
                .mapToInt(item -> item.getProduct().getSellPrice() * item.getQuantity())
                .sum());
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeCartProduct(String cartId, String productId) {
        Cart cart = getCart(cartId);
        cart.getListCartItem().removeIf(item -> item.getProduct().getId().equals(productId));
        cart.setTotalPrice(cart.getListCartItem().stream()
                .mapToInt(item -> item.getProduct().getSellPrice() * item.getQuantity())
                .sum());
        return cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(String cartId) {
        Cart cart = getCart(cartId);
        cart.getListCartItem().clear();
        cart.setTotalPrice(0);
        return cartRepository.save(cart);
    }

    @Override
    public List<CartItem> getAllCartItems(String cartId) {
        Cart cart = getCart(cartId);
        return cart.getListCartItem();
    }

}
