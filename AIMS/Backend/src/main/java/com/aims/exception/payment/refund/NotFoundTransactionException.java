package com.aims.exception.payment.refund;

import com.aims.exception.payment.PaymentException;

public class NotFoundTransactionException extends PaymentException {
    public NotFoundTransactionException() {
        super("Không tìm thấy giao dịch yêu cầu");
    }
}
