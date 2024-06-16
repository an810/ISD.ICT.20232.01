package com.aims.controller;

import com.aims.entity.cart.Cart;
import com.aims.entity.product.Product;
import com.aims.entity.response.AIMSResponse;
import com.aims.entity.user.User;
import com.aims.service.CartService;
import com.aims.utils.Constants;
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
    public ResponseEntity<AIMSResponse<Cart>> getCart(@PathVariable String cartId) {
        Cart cart =cartService.getCart(cartId);
        AIMSResponse<Cart> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get cart successfully", cart);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<AIMSResponse<Cart>> addCartProduct(@PathVariable String cartId, @RequestParam String productId, @RequestParam int quantity) {
        Cart cart = cartService.addCartProduct(cartId, productId, quantity);
        AIMSResponse<Cart> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Add product to cart successfully", cart);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cartId}/remove")
    public ResponseEntity<AIMSResponse<Cart>> removeCartProduct(@PathVariable String cartId, @RequestParam String productId) {
        Cart cart =  cartService.removeCartProduct(cartId, productId);
        AIMSResponse<Cart> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Remove product from cart successfully", cart);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cartId}/clear")
    public ResponseEntity<AIMSResponse<Cart>> clearCart(@PathVariable String cartId) {
        Cart cart = cartService.clearCart(cartId);
        AIMSResponse<Cart> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Clear cart successfully", cart);
        return ResponseEntity.ok(response);
    }

}
