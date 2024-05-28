package com.aims.controller;

import com.aims.entity.Cart.Cart;
import com.aims.entity.Product.Product;
import com.aims.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable String cartId) {
        return cartService.getCart(cartId);
    }

    @PostMapping("/{cartId}/add")
    public Cart addCartProduct(@PathVariable String cartId, @RequestParam String productId, @RequestParam int quantity) {
        return cartService.addCartProduct(cartId, productId, quantity);
    }

    @DeleteMapping("/{cartId}/remove")
    public Cart removeCartProduct(@PathVariable String cartId, @RequestParam String productId) {
        return cartService.removeCartProduct(cartId, productId);
    }

    @PostMapping("/{cartId}/clear")
    public Cart clearCart(@PathVariable String cartId) {
        return cartService.clearCart(cartId);
    }

    @GetMapping("/check-cart")
    public List<Product> checkCart(@RequestParam String cartId) {
        return cartService.checkCartProducts(cartId);
    }

}
