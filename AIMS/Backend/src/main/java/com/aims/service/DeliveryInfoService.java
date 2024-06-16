package com.aims.service;

import com.aims.entity.delivery.DeliveryInfo;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryInfoService {
    DeliveryInfo create(DeliveryInfo deliveryInfo);
    DeliveryInfo getDeliveryInfo(String id);
    Integer calculateShippingFee(String province, boolean isRushDelivery);
}
