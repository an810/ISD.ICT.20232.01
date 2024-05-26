package com.aims.entity.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private String cartId;
    private List<CartItem> listCartItem;
    private double totalPrice;

//    private static Cart cartInstance;
//
//    public static Cart getCart(){
//        if(cartInstance == null) cartInstance = new Cart();
//        return cartInstance;
//    }

    public List<CartItem> getItems() {
        return listCartItem;
    }
    public void addCartItem(CartItem cm){
        listCartItem.add(cm);
    }

    public void removeCartMedia(CartItem cartItem){
        listCartItem.remove(cartItem);
    }

    public void emptyCart(){
        listCartItem.clear();
    }

    public void addProductToCart(Product product, int quantity) {
        Optional<CartItem> existingCartItem = listCartItem.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            listCartItem.add(new CartItem(product, quantity));
        }
    }

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

//    public int setTotalPrice(){
//        int totalPrice = 0;
//        for (CartItem cartItem : listCartItem) {
//            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
//        }
//        return totalPrice;
//    }

}
