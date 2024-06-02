package com.aims.exception.payment.pay;

import com.aims.exception.payment.PaymentException;

public class RefundRejectedTransactionException extends PaymentException {
    public RefundRejectedTransactionException() {
        super("VNPAY: Giao dịch hoàn tiền bị từ chối");
    }
}
