package com.aims.exception.payment;

public class IncompletTransactionException extends PaymentException{
    public IncompletTransactionException() {
        super("VNPAY: Giao dịch chưa hoàn tất");
    }
}
