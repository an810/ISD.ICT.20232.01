package com.aims.exception.payment.pay;

import com.aims.exception.payment.PaymentException;

public class SuspiciousTransactionException extends PaymentException {
    public SuspiciousTransactionException() {
        super("VNPAY: Giao dịch nghi ngờ gian lận");
    }
}
