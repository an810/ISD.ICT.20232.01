package com.aims.controller;


import com.aims.entity.payment.PaymentTransaction;
import com.aims.entity.payment.RefundTransaction;
import com.aims.repository.PaymentTransactionRepository;
import com.aims.repository.RefundTransactionRepository;
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
    private final RefundTransactionRepository refundTransactionRepository;

    public PaymentController(VNPayService vnpayService,PaymentTransactionRepository paymentTransactionRepository, RefundTransactionRepository refundTransactionRepository) {
        this.vnpayService = vnpayService;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.refundTransactionRepository = refundTransactionRepository;
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
            RefundTransaction refundTransaction = vnpayService.refund(paymentTransaction);
            return refundTransactionRepository.save(refundTransaction);
        } catch (IOException e) {
            return new RefundTransaction();
        }
    }

    @PostMapping("/save-payment-transaction")
    public PaymentTransaction saveTransaction(@RequestBody Map<String, String> response) {
        PaymentTransaction paymentTransaction = vnpayService.savePaymentTransaction(response);
        return paymentTransactionRepository.save(paymentTransaction);
    }

}
