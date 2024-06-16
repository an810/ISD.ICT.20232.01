package com.aims.repository;

import com.aims.entity.product.DVD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDRepository extends MongoRepository<DVD, String> {
}
