package com.aims.exception.payment.refund;

import com.aims.exception.payment.PaymentException;

public class InvalidDataTypeException extends PaymentException {
    public InvalidDataTypeException() {
        super("Dữ liệu gửi sang không đúng định dạng");
    }
}
