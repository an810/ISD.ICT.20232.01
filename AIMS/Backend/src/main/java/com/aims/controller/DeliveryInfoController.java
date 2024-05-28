package com.aims.controller;

import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.service.DeliveryInfoService;
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
    public DeliveryInfo addDeliveryInfo(@RequestBody DeliveryInfo deliveryInfo) {
        return deliveryInfoService.create(deliveryInfo);
    }

    @GetMapping("/{id}")
    public DeliveryInfo getDeliveryInfo(@PathVariable String id) {
        return deliveryInfoService.getDeliveryInfo(id);
    }

    @GetMapping("/shipping-fee")
    public Double getShippingFee(@RequestParam String province, @RequestParam boolean isRushDelivery) {
        return deliveryInfoService.calculateShippingFee(province, isRushDelivery);
    }
}
