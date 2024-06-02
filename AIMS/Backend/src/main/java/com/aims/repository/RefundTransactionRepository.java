package com.aims.repository;

import com.aims.entity.payment.RefundTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefundTransactionRepository extends MongoRepository<RefundTransaction, String> {
}
