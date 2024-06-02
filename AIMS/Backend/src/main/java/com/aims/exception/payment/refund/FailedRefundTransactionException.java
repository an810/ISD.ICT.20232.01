package com.aims.exception.payment.refund;

import com.aims.exception.payment.PaymentException;

public class FailedRefundTransactionException extends PaymentException {
    public FailedRefundTransactionException() {
        super("Giao dịch này không thành công bên VNPAY. VNPAY từ chối xử lý yêu cầui");
    }
}
