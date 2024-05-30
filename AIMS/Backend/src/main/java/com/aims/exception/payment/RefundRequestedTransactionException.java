package com.aims.exception.payment;

public class RefundRequestedTransactionException extends PaymentException{
    public RefundRequestedTransactionException() {
        super("VNPAY: VNPAY đã gửi yêu cầu hoàn tiền sang Ngân hàng (GD hoàn tiền)");
    }
}
