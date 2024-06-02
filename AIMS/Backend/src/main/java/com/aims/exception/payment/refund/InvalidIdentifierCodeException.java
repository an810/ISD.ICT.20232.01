package com.aims.exception.payment.refund;

import com.aims.exception.payment.PaymentException;

public class InvalidIdentifierCodeException extends PaymentException {
    public InvalidIdentifierCodeException() {
        super("Mã định danh kết nối không hợp lệ (kiểm tra lại TmnCode)");
    }
}
