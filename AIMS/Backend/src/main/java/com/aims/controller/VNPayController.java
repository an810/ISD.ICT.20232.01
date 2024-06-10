package com.aims.controller;

import com.aims.entity.payment.PaymentTransaction;
import com.aims.entity.payment.RefundTransaction;
import com.aims.entity.response.AIMSResponse;
import com.aims.repository.PaymentTransactionRepository;
import com.aims.repository.RefundTransactionRepository;
import com.aims.subsystem.vnpay.VNPayService;
import com.aims.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/payment")
public class VNPayController {

    private final VNPayService vnpayService;
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final RefundTransactionRepository refundTransactionRepository;

    public VNPayController(VNPayService vnpayService, PaymentTransactionRepository paymentTransactionRepository, RefundTransactionRepository refundTransactionRepository) {
        this.vnpayService = vnpayService;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.refundTransactionRepository = refundTransactionRepository;
    }

    @GetMapping("/pay")
    public ResponseEntity<AIMSResponse<String>> generateUrl(@RequestParam int amount, @RequestParam String orderId) throws IOException {
        String result = vnpayService.generateUrl(amount, orderId);
        AIMSResponse<String> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Success", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refund")
    public ResponseEntity<AIMSResponse<RefundTransaction>> refund(@RequestBody PaymentTransaction paymentTransaction) throws IOException {
            RefundTransaction refundTransaction = vnpayService.refund(paymentTransaction);
            refundTransactionRepository.save(refundTransaction);
            AIMSResponse<RefundTransaction> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Refund successfully", refundTransaction);
            return ResponseEntity.ok(response);
    }

    @PostMapping("/save-payment-transaction")
    public ResponseEntity<AIMSResponse<PaymentTransaction>> saveTransaction(@RequestBody Map<String, String> response) {
        PaymentTransaction paymentTransaction = vnpayService.savePaymentTransaction(response);
        paymentTransactionRepository.save(paymentTransaction);
        AIMSResponse<PaymentTransaction> res = new AIMSResponse<>(Constants.SUCCESS_CODE, "Save payment transaction successfully", paymentTransaction);
        return ResponseEntity.ok(res);
    }

}
