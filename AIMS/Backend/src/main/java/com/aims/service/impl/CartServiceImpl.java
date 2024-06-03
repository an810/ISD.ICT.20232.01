package com.aims.service.impl;

import com.aims.entity.Cart.Cart;
import com.aims.entity.Cart.CartItem;
import com.aims.entity.Product.Product;
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

    /** 
     * 
     * Data coupling:
     * Data coupling between CartServiceImpl and the repository classes
     * -> any change in the repository classes can affect CartServiceImpl. * 
     * Explanation: The CartServiceImpl class depends on specific instances of CartRepository and ProductRepository.
     * 
     * Functional cohesion:
     * Explanation: The constructor initializes the repositories.
     * 
     */
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }
    
    /** 
     * Control coupling:
     * This control coupling implies that CartServiceImpl is responsible for controlling the data retrieval process from CartRepository.
     * Explanation: The CartServiceImpl class directly calls the findById method on CartRepository.
     * 
     * Functional cohesion:
     * Method performs a single task of retrieving or initializing a cart.
     * 
     */
    public Cart getCart(String cartId) {
        // Ensure the cart is properly initialized
        return cartRepository.findById(cartId).orElse(new Cart(cartId, new ArrayList<>(), 0));
    }

    /** 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The CartServiceImpl class directly interacts with the ProductRepository to fetch a product.
     *  
     * Data coupling:
     * Directly updating cart's total price
     * Explanation: The CartServiceImpl class directly modifies the internal state of the Cart object by updating its total price.
     * 
     * Control coupling:
     * Method call on cartRepository
     * Explanation: The CartServiceImpl class saves the updated cart back to the CartRepository.
     * 
     * Functional Cohesion: 
     * Method handles adding a product to the cart.
     * 
     * Sequential Cohesion: 
     * Steps to add a product are performed in sequence.
     * 
     */
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
                .mapToDouble(item -> item.getProduct().getSellPrice() * item.getQuantity())
                .sum());
        return cartRepository.save(cart);
    }

    /**
     * Data coupling:
     * Directly updating cart's total price
     * Explanation: The CartServiceImpl class handles the logic for recalculating the total price after removing a product.
     * 
     * Control coupling:
     * Method call on cartRepository
     * Explanation: The service directly saves the modified cart back to the repository, controlling the persistence flow.
     * 
     * Functional Cohesion: 
     * Method handles removing a product from the cart.
     * 
     * Sequential Cohesion: 
     * Steps to remove a product are performed in sequence.
     * 
     */
    public Cart removeCartProduct(String cartId, String productId) {
        Cart cart = getCart(cartId);
        cart.getListCartItem().removeIf(item -> item.getProduct().getId().equals(productId));
        cart.setTotalPrice(cart.getListCartItem().stream()
                .mapToDouble(item -> item.getProduct().getSellPrice() * item.getQuantity())
                .sum());
        return cartRepository.save(cart);
    }

    /**
     * 
     * Data coupling:
     * Directly updating cart's total price
     * Explanation: The service sets the total price to zero directly, implying that the business rule for clearing the cart is managed here.
     * 
     * Control coupling:
     * Method call on cartRepository
     * Explanation: The service manages the persistence of the cleared cart, indicating control over the repository interaction.
     * 
     * Functional Cohesion: 
     * Method handles clearing the cart.
     * 
     * Sequential Cohesion: 
     * Steps to clear the cart are performed in sequence.
     * 
     */
    public Cart clearCart(String cartId) {
        Cart cart = getCart(cartId);
        cart.getListCartItem().clear();
        cart.setTotalPrice(0);
        return cartRepository.save(cart);
    }

    /**
     * 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The service directly checks each product's availability by interacting with ProductRepository.
     * 
     * Functional Cohesion: 
     * Method handles checking product availability in the cart.
     * 
     * Communicational Cohesion: 
     * Works with cart items and product data.
     * 
     */
    public List<Product> checkCartProducts(String cartId) {
        Cart cart = getCart(cartId);
        List<Product> products = new ArrayList<>();
        for (CartItem item : cart.getListCartItem()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotAvailableException("Product not found"));
            if (product.getQuantity() < item.getQuantity()) {
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Functional Cohesion: 
     * Method handles retrieving all cart items.
     * 
     * Communicational Cohesion: 
     * Works with cart data.
     */
    public List<CartItem> getAllCartItems(String cartId) {
        Cart cart = getCart(cartId);
        return cart.getListCartItem();
    }

}
