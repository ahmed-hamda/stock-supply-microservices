package com.stock.product_service.service;

import com.stock.product_service.dto.ProductRequest;
import com.stock.product_service.dto.ProductResponse;
import com.stock.product_service.entity.Product;
import com.stock.product_service.event.StockLowEvent;
import com.stock.product_service.exception.InvalidQuantityException;
import com.stock.product_service.exception.ProductAlreadyExistsException;
import com.stock.product_service.exception.ProductNotFoundException;
import com.stock.product_service.kafka.StockEventProducer;
import com.stock.product_service.mapper.ProductMapper;
import com.stock.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StockEventProducer stockEventProducer;

    public ProductService(ProductRepository productRepository, StockEventProducer stockEventProducer) {
        this.productRepository = productRepository;
        this.stockEventProducer = stockEventProducer;
    }

    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsByReference(request.getReference())) {
            throw new ProductAlreadyExistsException(request.getReference());
        }

        Product product = ProductMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = findProductEntityById(id);
        return ProductMapper.toResponse(product);
    }

    public ProductResponse getProductByReference(String reference) {
        Product product = productRepository.findByReference(reference)
                .orElseThrow(() -> new ProductNotFoundException(reference));

        return ProductMapper.toResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = findProductEntityById(id);

        if (!product.getReference().equals(request.getReference())
                && productRepository.existsByReference(request.getReference())) {
            throw new ProductAlreadyExistsException(request.getReference());
        }

        product.setName(request.getName());
        product.setReference(request.getReference());
        product.setQuantity(request.getQuantity());
        product.setMinStock(request.getMinStock());
        product.setSellingPrice(request.getSellingPrice());

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = findProductEntityById(id);
        productRepository.delete(product);
    }

    public ProductResponse increaseStock(Long id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidQuantityException();
        }

        Product product = findProductEntityById(id);
        product.setQuantity(product.getQuantity() + quantity);

        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResponse(updatedProduct);
    }

    public ProductResponse decreaseStock(Long id, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidQuantityException();
        }

        Product product = findProductEntityById(id);

        if (product.getQuantity() < quantity) {
            throw new InvalidQuantityException();
        }

        product.setQuantity(product.getQuantity() - quantity);

        Product updatedProduct = productRepository.save(product);

        if (updatedProduct.getQuantity() < updatedProduct.getMinStock()) {
            StockLowEvent event = new StockLowEvent(
                    updatedProduct.getId(),
                    updatedProduct.getReference(),
                    updatedProduct.getQuantity(),
                    updatedProduct.getMinStock()
            );

            stockEventProducer.publishStockLowEvent(event);
        }

        return ProductMapper.toResponse(updatedProduct);
    }
    public boolean isStockBelowMin(Long id) {
        Product product = findProductEntityById(id);
        return product.getQuantity() < product.getMinStock();
    }

    private Product findProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}