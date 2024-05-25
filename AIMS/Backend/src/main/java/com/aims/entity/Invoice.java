package com.aims.entity;


import com.aims.entity.Order.Order;

public class Invoice {

    private Order order;
    private int amount;

    public Invoice(){

    }

    public Invoice(Order order){
        this.order = order;
    }
    public static Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    public Order getOrder() {
        return order;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void saveInvoice(){

    }
}
