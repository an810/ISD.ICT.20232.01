package com.aims.controller;

import com.aims.entity.product.Book;
import com.aims.entity.product.CD;
import com.aims.entity.product.DVD;
import com.aims.entity.product.Product;
import com.aims.entity.response.AIMSResponse;
import com.aims.service.ProductService;
import com.aims.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<AIMSResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.findAllProduct();
        AIMSResponse<List<Product>> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get all products successfully", products);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-cd")
    public ResponseEntity<AIMSResponse<Product>> addCD(@RequestBody CD product) {
        Product prod = productService.addCD(product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Add CD successfully", prod);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-book")
    public ResponseEntity<AIMSResponse<Product>> addBook(@RequestBody Book product) {
        Product prod = productService.addBook(product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Add book successfully", prod);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-dvd")
    public ResponseEntity<AIMSResponse<Product>> addDVD(@RequestBody DVD product) {
        Product prod = productService.addDVD(product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Add DVD successfully", prod);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AIMSResponse<Product>> findProduct(@PathVariable String id) {
        Product product = productService.findProductById(id);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get product successfully", product);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-cd/{id}")
    public ResponseEntity<AIMSResponse<Product>> updateCD(@PathVariable String id, @RequestBody CD product) {
        Product newProduct = productService.updateCD(id, product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update CD successfully", newProduct);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-book/{id}")
    public ResponseEntity<AIMSResponse<Product>> updateBook(@PathVariable String id, @RequestBody Book product) {
        Product newProduct = productService.updateBook(id, product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update book successfully", newProduct);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-dvd/{id}")
    public ResponseEntity<AIMSResponse<Product>> updateDVD(@PathVariable String id, @RequestBody DVD product) {
        Product newProduct = productService.updateDVD(id, product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update DVD successfully", newProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AIMSResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Delete product successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<AIMSResponse<Void>> deleteListProduct(@RequestParam List<String> ids) {
        productService.deleteListProduct(ids);
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Delete list product successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-price/{id}")
    public ResponseEntity<AIMSResponse<Product>> updatePrice(@PathVariable String id, @RequestParam int newPrice) {
        Product newProduct = productService.updatePrice(id, newPrice);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update price successfully", newProduct);
        return ResponseEntity.ok(response);
    }


}
