package com.stock.product_service.mapper;

import com.stock.product_service.dto.ProductRequest;
import com.stock.product_service.dto.ProductResponse;
import com.stock.product_service.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setReference(request.getReference());
        product.setQuantity(request.getQuantity());
        product.setMinStock(request.getMinStock());
        product.setSellingPrice(request.getSellingPrice());
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getReference(),
                product.getQuantity(),
                product.getMinStock(),
                product.getSellingPrice()
        );
    }
}