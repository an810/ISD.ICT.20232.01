package com.aims.service.impl;

import com.aims.entity.product.Product;
import com.aims.exception.PriceInflationException;
import com.aims.exception.ProductNotAvailableException;
import com.aims.repository.ProductRepository;
import com.aims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(String id){
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return product;
        } else {
            throw new ProductNotAvailableException("Product not found");
        }
    }

    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product){
        if(productRepository.existsById(product.getId())){
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        else throw new ProductNotAvailableException("Product not found");
    }

    @Override
    public Product updatePrice(String productId, int newPrice) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            int currentPrice = product.getSellPrice();
            if (newPrice < currentPrice * 0.3 || newPrice > currentPrice * 1.5) {
                throw new PriceInflationException("New price is not valid. It should be between 30% and 150% of the current price.");
            }
            product.setSellPrice(newPrice);
            return productRepository.save(product);
        } else {
            throw new ProductNotAvailableException("Product not found");
        }
    }

    @Override
    public boolean checkAvailableProduct(String productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            return product.getQuantity() >= quantity;
        } else {
            throw new ProductNotAvailableException("Product not found");
        }
    }

}
