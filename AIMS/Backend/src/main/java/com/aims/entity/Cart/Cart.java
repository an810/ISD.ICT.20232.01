package com.aims.entity.Cart;

import java.util.ArrayList;
import java.util.List;

import com.aims.exception.ProductNotAvailableException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "cart")
public class Cart {
    @Id
    private String id;
    private List<CartItem> listCartItem;
    private double totalPrice;


//    public void checkAvailabilityOfProduct() throws ProductNotAvailableException{
//        boolean allAvai = true;
//        for (CartItem object : listCartItem) {
//            CartItem cartItem = (CartItem) object;
//            int requiredQuantity = cartItem.getQuantity();
//            int availQuantity = cartItem.getProduct().getQuantity();
//            if (requiredQuantity > availQuantity) allAvai=false;
//        }
//        if (!allAvai) throw new ProductNotAvailableException("Some media not available");
//    }

}
