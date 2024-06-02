package com.aims.subsystem;

import com.aims.entity.payment.PaymentTransaction;
import com.aims.entity.payment.RefundTransaction;
import com.aims.exception.AIMSException;
import com.aims.exception.payment.PaymentException;

import java.io.IOException;
import java.util.Map;

public interface IPaySubsystem {

    PaymentTransaction getPaymentTransaction(Map<String, String> response) throws PaymentException, AIMSException, IOException;
    String generateUrl(int amount, String orderId) throws IOException;
    RefundTransaction refund(PaymentTransaction paymentTransaction) throws IOException;

}
