package com.aims.exception.payment;

public class RefundRejectedTransactionException extends PaymentException{
    public RefundRejectedTransactionException() {
        super("VNPAY: Giao dịch hoàn tiền bị từ chối");
    }
}
