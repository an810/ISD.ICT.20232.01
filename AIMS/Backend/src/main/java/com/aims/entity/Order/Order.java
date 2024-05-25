package com.aims.entity.Order;

import com.aims.entity.Media.Media;
import com.aims.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@Document(collection = "order")
public class Order {
    private int shippingFees;
    private Media[] media;
    private String userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhone;


//    public Order(){
//        this.listOrderMedia = new ArrayList<>();
//    }
//
//    public Order(List lstOrderMedia) {
//        this.listOrderMedia = listOrderMedia;
//    }
//
//    public void addOrderMedia(OrderMedia om){
//        this.listOrderMedia.add(om);
//    }
//
//    public void removeOrderMedia(OrderMedia om){
//        this.listOrderMedia.remove(om);
//    }
//
//    public List getlistOrderMedia() {
//        return this.listOrderMedia;
//    }
//
//    public void setlistOrderMedia(List listOrderMedia) {
//        this.listOrderMedia = listOrderMedia;
//    }
//
//    public void setShippingFees(int shippingFees) {
//        this.shippingFees = shippingFees;
//    }
//
//    public int getShippingFees() {
//        return shippingFees;
//    }
//
//    public int getAmount(){
//        double amount = 0;
//        for (Object object : listOrderMedia) {
//            OrderMedia om = (OrderMedia) object;
//            amount += om.getPrice();
//        }
//        return (int) (amount + (Constants.PERCENT_VAT/100)*amount);
//    }

}