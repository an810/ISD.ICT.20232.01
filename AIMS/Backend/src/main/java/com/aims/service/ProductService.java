package com.aims.service;

import com.aims.entity.Product.Product;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    public List<Product> findAllProduct();

    public Optional<Product> findProductById(String id);
}
