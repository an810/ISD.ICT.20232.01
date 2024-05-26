package com.aims.repository;

import com.aims.entity.Cart.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    // You can define additional query methods here if needed
}
