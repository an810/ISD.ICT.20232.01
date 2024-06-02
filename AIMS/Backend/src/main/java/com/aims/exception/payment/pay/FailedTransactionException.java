package com.aims.exception.payment.pay;

import com.aims.exception.payment.PaymentException;

public class FailedTransactionException extends PaymentException {
    public FailedTransactionException() {
        super("VNPAY: Giao dịch bị lỗi");
    }
}
