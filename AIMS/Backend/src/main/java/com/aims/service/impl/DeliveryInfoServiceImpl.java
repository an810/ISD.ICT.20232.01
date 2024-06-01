package com.aims.service.impl;


import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.repository.DeliveryInfoRepository;
import com.aims.service.DeliveryInfoService;
import com.aims.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class DeliveryInfoServiceImpl implements DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;
    
    /**
     * Data coupling:
     * creates data coupling with the DeliveryInfoRepository
     * Explanation: DeliveryInfoServiceImpl depends on the specific instance of DeliveryInfoRepository.
     * 
     * @param deliveryInfoRepository
     */
    public DeliveryInfoServiceImpl(DeliveryInfoRepository deliveryInfoRepository) {
        this.deliveryInfoRepository = deliveryInfoRepository;
    }

    /**
     * 
     * Control coupling
     * Method call on deliveryInfoRepository
     * Explanation: DeliveryInfoServiceImpl directly calls the save method on DeliveryInfoRepository.
     * 
     */
    @Override
    public DeliveryInfo create(DeliveryInfo deliveryInfo) {
        return deliveryInfoRepository.save(deliveryInfo);
    }

    /**
     * 
     * Control coupling
     * Method call on deliveryInfoRepository
     * Explanation: DeliveryInfoServiceImpl directly calls the save method on DeliveryInfoRepository.
     * 
     */
    @Override
    public DeliveryInfo getDeliveryInfo(String id) {
        return deliveryInfoRepository.findById(id).orElse(null);
    }

    /**
     * Data coupling:
     * Using constants directly for shipping fee calculation
     * Explanation: The method relies on constants defined in the Constants class for determining shipping fees.
     * 
     */
    @Override
    public Double calculateShippingFee(String province, boolean isRushDelivery) {
        double shippingFee = 0.0;
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
