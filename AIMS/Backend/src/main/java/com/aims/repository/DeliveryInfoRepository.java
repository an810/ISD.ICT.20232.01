package com.aims.repository;

import com.aims.entity.DeliveryInfo.DeliveryInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInfoRepository extends MongoRepository<DeliveryInfo, String> {
}
