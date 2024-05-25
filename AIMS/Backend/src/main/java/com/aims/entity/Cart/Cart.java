package com.aims.entity.Cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aims.entity.Media.Media;
import com.aims.exception.MediaNotAvailableException;

public class Cart {

    private List<CartMedia> listCartMedia;
    private static Cart cartInstance;

    public static Cart getCart(){
        if(cartInstance == null) cartInstance = new Cart();
        return cartInstance;
    }

    private Cart(){
        listCartMedia = new ArrayList<>();
    }

    public void addCartMedia(CartMedia cm){
        listCartMedia.add(cm);
    }

    public void removeCartMedia(CartMedia cm){
        listCartMedia.remove(cm);
    }

    public List getListMedia(){
        return listCartMedia;
    }

    public void emptyCart(){
        listCartMedia.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : listCartMedia) {
            CartMedia cm = (CartMedia) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : listCartMedia) {
            CartMedia cm = (CartMedia) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvai = true;
        for (Object object : listCartMedia) {
            CartMedia cartMedia = (CartMedia) object;
            int requiredQuantity = cartMedia.getQuantity();
            int availQuantity = cartMedia.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvai = false;
        }
        if (!allAvai) throw new MediaNotAvailableException("Some media not available");
    }

    public CartMedia checkMediaInCart(Media media){
        for (CartMedia cartMedia : listCartMedia) {
            if (cartMedia.getMedia().getId() == media.getId()) return cartMedia;
        }
        return null;
    }

}
