package com.aims.exception.payment.pay;

import com.aims.exception.payment.PaymentException;

public class ProcessingTransactionException extends PaymentException {
    public ProcessingTransactionException() {
        super("VNPAY: VNPAY đang xử lý giao dịch này (GD hoàn tiền)");
    }
}
