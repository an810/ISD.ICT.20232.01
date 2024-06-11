package com.aims.service;

import com.aims.entity.product.Book;
import com.aims.entity.product.CD;
import com.aims.entity.product.DVD;
import com.aims.entity.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAllProduct();
    Product findProductById(String id);

    Product addCD(CD product);
    Product addBook(Book product);
    Product addDVD(DVD product);

    Product updateCD(String id, CD product);
    Product updateBook(String id, Book product);
    Product updateDVD(String id, DVD product);

    void deleteProduct(String id);

    Product updatePrice(String productId, int newPrice);

    boolean checkAvailableProduct(String productId, int quantity);
}
