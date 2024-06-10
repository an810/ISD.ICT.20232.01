package com.aims.controller;

import com.aims.entity.cart.Cart;
import com.aims.entity.delivery.DeliveryInfo;
import com.aims.entity.response.AIMSResponse;
import com.aims.service.DeliveryInfoService;
import com.aims.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/delivery-info")
public class DeliveryInfoController {
    private final DeliveryInfoService deliveryInfoService;

    public DeliveryInfoController(DeliveryInfoService deliveryInfoService) {
        this.deliveryInfoService = deliveryInfoService;
    }

    @PostMapping("/add")
    public ResponseEntity<AIMSResponse<DeliveryInfo>>  addDeliveryInfo(@RequestBody DeliveryInfo deliveryInfo) {
        DeliveryInfo data = deliveryInfoService.create(deliveryInfo);
        AIMSResponse<DeliveryInfo> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Create delivery info successfully", data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AIMSResponse<DeliveryInfo>> getDeliveryInfo(@PathVariable String id) {
        DeliveryInfo data = deliveryInfoService.getDeliveryInfo(id);
        AIMSResponse<DeliveryInfo> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get delivery info successfully", data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/shipping-fee")
    public ResponseEntity<AIMSResponse<Integer>> getShippingFee(@RequestParam String province, @RequestParam boolean isRushDelivery) {
        Integer fee = deliveryInfoService.calculateShippingFee(province, isRushDelivery);
        AIMSResponse<Integer> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Calculate shipping fee successfully", fee);
        return ResponseEntity.ok(response);
    }
}
