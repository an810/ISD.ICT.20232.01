package com.aims.service.impl;


import com.aims.entity.delivery.DeliveryInfo;
import com.aims.repository.DeliveryInfoRepository;
import com.aims.service.DeliveryInfoService;
import com.aims.utils.Constants;
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

    @Override
    public DeliveryInfo getDeliveryInfo(String id) {
        return deliveryInfoRepository.findById(id).orElse(null);
    }

    @Override
    public Integer calculateShippingFee(String province, boolean isRushDelivery) {
        int shippingFee = 0;
        for (String provinceName : Constants.NORTHERN_VIETNAM) {
            if (province.equalsIgnoreCase(provinceName)) {
                shippingFee = Constants.SHIPPING_FEE_NORTHERN_VIETNAM;
                break;
            }
        }
        if (province.equalsIgnoreCase("HaNoi") && isRushDelivery) {
            shippingFee = Constants.RUSH_SHIPPING_FEE;
        }
        for (String provinceName : Constants.CENTRAL_VIETNAM) {
            if (province.equalsIgnoreCase(provinceName)) {
                shippingFee = Constants.SHIPPING_FEE_CENTRAL_VIETNAM;
                break;
            }
        }
        for (String provinceName : Constants.SOUTHERN_VIETNAM) {
            if (province.equalsIgnoreCase(provinceName)) {
                shippingFee = Constants.SHIPPING_FEE_SOUTHERN_VIETNAM;
                break;
            }
        }
        return shippingFee;
    }
}
