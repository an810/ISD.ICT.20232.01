package com.aims.service;

import com.aims.entity.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAllProduct();

    Product findProductById(String id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    Product updatePrice(String productId, int newPrice);
    boolean checkAvailableProduct(String productId, int quantity);
}
