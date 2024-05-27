package com.aims.service.impl;


import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.repository.DeliveryInfoRepository;
import com.aims.service.DeliveryInfoService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInfoServiceImpl implements DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;
    public DeliveryInfoServiceImpl(DeliveryInfoRepository deliveryInfoRepository) {
        this.deliveryInfoRepository = deliveryInfoRepository;
    }

    @Override
    public DeliveryInfo create(DeliveryInfo deliveryInfo) {
        return deliveryInfoRepository.save(deliveryInfo);
    }

}
