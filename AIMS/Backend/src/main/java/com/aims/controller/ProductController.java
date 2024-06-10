package com.aims.controller;

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

    @PostMapping("/add")
    public ResponseEntity<AIMSResponse<Product>> addProduct(@RequestBody Product product) {
        Product prod = productService.addProduct(product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Add product successfully", prod);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AIMSResponse<Product>> findProduct(@PathVariable String id) {
        Product product = productService.findProductById(id);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Get product successfully", product);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AIMSResponse<Product>> updateProduct(@RequestBody Product product) {
        Product newProduct = productService.updateProduct(product);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update product successfully", newProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AIMSResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        AIMSResponse<Void> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Delete product successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-price/{id}")
    public ResponseEntity<AIMSResponse<Product>> updatePrice(@PathVariable String id, @RequestParam int newPrice) {
        Product newProduct = productService.updatePrice(id, newPrice);
        AIMSResponse<Product> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Update price successfully", newProduct);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-availability/{id}")
    public ResponseEntity<AIMSResponse<Boolean>> checkAvailableProduct(@PathVariable String id, @RequestParam int quantity) {
        Boolean avail = productService.checkAvailableProduct(id, quantity);
        AIMSResponse<Boolean> response = new AIMSResponse<>(Constants.SUCCESS_CODE, "Check availability successfully", avail);
        return ResponseEntity.ok(response);
    }

}
