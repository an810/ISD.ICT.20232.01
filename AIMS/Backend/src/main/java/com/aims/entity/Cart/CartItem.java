package com.aims.entity.Cart;

import com.aims.entity.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CartItem {
    private Product product;
    private int quantity;
    // Other fields and methods...
}


