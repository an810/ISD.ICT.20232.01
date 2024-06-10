package com.aims.repository;

import com.aims.entity.product.CD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRepository extends MongoRepository<CD, String>{
}
