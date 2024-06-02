package com.aims.entity.invoice;


import com.aims.entity.order.Order;
import com.aims.entity.payment.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "invoice")
@Data
@AllArgsConstructor
public class Invoice {
    @Id
    private String invoiceId;
    private Order order;
    private PaymentTransaction paymentTransaction;
    private int totalAmount;

    public Invoice(Order order){
        this.order = order;
    }
    public static Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

}
