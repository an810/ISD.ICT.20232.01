package com.aims.exception.payment;

public class FailedTransactionException extends PaymentException{
    public FailedTransactionException() {
        super("VNPAY: Giao dịch bị lỗi");
    }
}
