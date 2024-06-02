package com.aims.controller;


import com.aims.entity.payment.PaymentTransaction;
import com.aims.entity.payment.RefundTransaction;
import com.aims.repository.PaymentTransactionRepository;
import com.aims.subsystem.vnpay.VNPayService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final VNPayService vnpayService;
    private final PaymentTransactionRepository paymentTransactionRepository;

    public PaymentController(VNPayService vnpayService,PaymentTransactionRepository paymentTransactionRepository) {
        this.vnpayService = vnpayService;
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @GetMapping("/pay")
    public String generateUrl(@RequestParam int amount, @RequestParam String orderId) {
        try {
            return vnpayService.generateUrl(amount, orderId);
        } catch (IOException e) {
            return "Error";
        }
    }

    @GetMapping("/refund")
    public RefundTransaction refund(@RequestBody PaymentTransaction paymentTransaction) {
        try {
            return vnpayService.refund(paymentTransaction);
        } catch (IOException e) {
            return new RefundTransaction();
        }
    }

    @GetMapping("/get-payment-transaction")
    public PaymentTransaction getResponse(@RequestBody Map<String, String> response) {
        PaymentTransaction paymentTransaction = vnpayService.getPaymentTransaction(response);
        paymentTransactionRepository.save(paymentTransaction);
        return paymentTransaction;
    }

}
