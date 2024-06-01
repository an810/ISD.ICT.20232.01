package com.aims.service.impl;

import com.aims.entity.Product.Product;
import com.aims.exception.ProductNotAvailableException;
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
    
    /**
     * 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The service directly checks each product's availability by interacting with ProductRepository.
     * 
     */
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
    
    /**
     * 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The service directly checks each product's availability by interacting with ProductRepository.
     * 
     */
    @Override
    public Optional<Product> findProductById(String id){
        return productRepository.findById(id);
    }

    /**
     * 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The service directly checks each product's availability by interacting with ProductRepository.
     * 
     */
    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The service directly checks each product's availability by interacting with ProductRepository.
     * 
     */
    @Override
    public Product updateProduct(Product product){
        if(productRepository.existsById(product.getId())){
            return productRepository.save(product);
        }
        return null;
    }

    /**
     * 
     * Control coupling:
     * Method call on productRepository
     * Explanation: The service directly checks each product's availability by interacting with ProductRepository.
     * 
     */
    @Override
    public void deleteProduct(String id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        else throw new ProductNotAvailableException("Product not found");
    }

}
