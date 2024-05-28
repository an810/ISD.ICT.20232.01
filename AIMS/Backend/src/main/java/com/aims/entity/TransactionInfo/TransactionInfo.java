package com.aims.entity.TransactionInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "transaction_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfo {
    private String transactionId;
    private String transactionType; // credit cart, vnpay, ...
    private int transactionCode; // code response from bank
    private double amount;
    private String content;
    private LocalDate transactionDate;
}
