package com.aims.service.impl;

import com.aims.entity.Media.Product;
import com.aims.repository.ProductRepository;
import com.aims.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> findProductById(ObjectId id){
        return productRepository.findById(id);
    }
}
