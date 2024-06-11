package com.aims.service.impl;

import com.aims.entity.product.Book;
import com.aims.entity.product.CD;
import com.aims.entity.product.DVD;
import com.aims.entity.product.Product;
import com.aims.exception.PriceInflationException;
import com.aims.exception.ProductNotAvailableException;
import com.aims.repository.BookRepository;
import com.aims.repository.CDRepository;
import com.aims.repository.DVDRepository;
import com.aims.repository.ProductRepository;
import com.aims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CDRepository cdRepository;
    private final DVDRepository dvdRepository;
    private final BookRepository bookRepository;
    public ProductServiceImpl(ProductRepository productRepository, CDRepository cdRepository,
                              DVDRepository dvdRepository, BookRepository bookRepository) {
        this.productRepository = productRepository;
        this.cdRepository = cdRepository;
        this.dvdRepository = dvdRepository;
        this.bookRepository = bookRepository;
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
    public Product addCD(CD product){
        return cdRepository.save(product);
    }

    @Override
    public Product addBook(Book product){
        return bookRepository.save(product);
    }

    @Override
    public Product addDVD(DVD product){
        return dvdRepository.save(product);
    }


    @Override
    public Product updateCD(String id, CD product){
        CD product1 = cdRepository.findById(id).orElse(null);
        if (product1 != null) {
            cdRepository.delete(product1);
            return cdRepository.save(product);
        } else {
            throw new ProductNotAvailableException("Product not found");
        }
    }

    @Override
    public Product updateBook(String id, Book product){
        Book product1 = bookRepository.findById(id).orElse(null);
        if (product1 != null) {
            bookRepository.delete(product1);
            return bookRepository.save(product);
        } else {
            throw new ProductNotAvailableException("Product not found");
        }
    }

    @Override
    public Product updateDVD(String id, DVD product){
        DVD product1 = dvdRepository.findById(id).orElse(null);
        if (product1 != null) {
            dvdRepository.delete(product1);
            return dvdRepository.save(product);
        } else {
            throw new ProductNotAvailableException("Product not found");
        }
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
