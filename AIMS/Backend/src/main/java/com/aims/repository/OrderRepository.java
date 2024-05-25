package com.aims.repository;

import com.aims.entity.Order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // You can define additional query methods here if needed
}
