package com.aims.entity.Cart;

import com.aims.entity.Media.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private String productId;
    private int quantity;
    private int price;
}


