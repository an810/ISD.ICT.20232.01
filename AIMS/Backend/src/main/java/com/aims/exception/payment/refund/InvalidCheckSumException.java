package com.aims.exception.payment.refund;

import com.aims.exception.payment.PaymentException;

public class InvalidCheckSumException extends PaymentException {
    public InvalidCheckSumException() {
        super("Checksum không hợp lệ");
    }
}
