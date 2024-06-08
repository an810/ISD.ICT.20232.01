package com.aims.entity.invoice;


import com.aims.entity.order.Order;
import com.aims.entity.payment.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    private String invoiceId;
    private Order order;
    private PaymentTransaction paymentTransaction;
}
