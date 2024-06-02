package com.aims.entity.cart;

import com.aims.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private Product product;
    private int quantity;
    // Other fields and methods...
}


