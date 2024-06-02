package com.aims.exception.payment.refund;

import com.aims.exception.payment.PaymentException;

public class ProcessingRefundException extends PaymentException {
    public ProcessingRefundException() {
        super("Yêu cầu trùng lặp, duplicate request trong thời gian giới hạn của API");
    }
}
