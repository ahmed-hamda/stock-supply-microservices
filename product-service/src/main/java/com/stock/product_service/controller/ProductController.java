package com.stock.product_service.controller;

import com.stock.product_service.dto.ProductRequest;
import com.stock.product_service.dto.ProductResponse;
import com.stock.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ Create product
    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    // ✅ Get all products
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // ✅ Get product by reference (IMPORTANT pour ton projet)
    @GetMapping("/reference/{reference}")
    public ProductResponse getProductByReference(@PathVariable String reference) {
        return productService.getProductByReference(reference);
    }

    // ✅ Update product
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        return productService.updateProduct(id, request);
    }

    // ✅ Delete product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // ✅ Increase stock
    @PatchMapping("/{id}/increase-stock")
    public ProductResponse increaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        return productService.increaseStock(id, quantity);
    }

    // ✅ Decrease stock
    @PatchMapping("/{id}/decrease-stock")
    public ProductResponse decreaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        return productService.decreaseStock(id, quantity);
    }

    // ✅ Check if stock < minStock (TRÈS IMPORTANT pour Supply)
    @GetMapping("/{id}/check-stock")
    public boolean isStockBelowMin(@PathVariable Long id) {
        return productService.isStockBelowMin(id);
    }
}