package com.aims.entity.Order;

import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.entity.Media.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "order")
public class Order {
    @Id
    private String orderId;
    private List<OrderItem> listOrderItem;
    private DeliveryInfo deliveryInfo;
    private int totalAmount;

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