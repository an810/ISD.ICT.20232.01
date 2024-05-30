package com.aims.exception.payment;

public class SuspiciousTransactionException extends PaymentException{
    public SuspiciousTransactionException() {
        super("VNPAY: Giao dịch nghi ngờ gian lận");
    }
}
