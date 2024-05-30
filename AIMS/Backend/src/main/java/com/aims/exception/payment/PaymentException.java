package com.aims.exception.payment;

public class PaymentException extends RuntimeException{
    public PaymentException(String message) {
        super(message);
    }
}
