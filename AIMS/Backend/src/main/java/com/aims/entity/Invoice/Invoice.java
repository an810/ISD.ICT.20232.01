package com.aims.entity.Invoice;


import com.aims.entity.Order.Order;
import com.aims.entity.TransactionInfo.TransactionInfo;
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
    private TransactionInfo transactionInfo;
    private int totalAmount;

    public Invoice(Order order){
        this.order = order;
    }
    public static Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

}
