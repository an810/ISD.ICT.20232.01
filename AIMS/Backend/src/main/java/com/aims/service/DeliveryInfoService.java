package com.aims.service;

import com.aims.entity.DeliveryInfo.DeliveryInfo;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryInfoService {
    DeliveryInfo create(DeliveryInfo deliveryInfo);
    DeliveryInfo getDeliveryInfo(String id);
    Double calculateShippingFee(String province, boolean isRushDelivery);
}
