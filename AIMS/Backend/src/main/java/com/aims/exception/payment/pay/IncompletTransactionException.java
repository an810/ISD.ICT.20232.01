package com.aims.exception.payment.pay;

import com.aims.exception.payment.PaymentException;

public class IncompletTransactionException extends PaymentException {
    public IncompletTransactionException() {
        super("VNPAY: Giao dịch chưa hoàn tất");
    }
}
