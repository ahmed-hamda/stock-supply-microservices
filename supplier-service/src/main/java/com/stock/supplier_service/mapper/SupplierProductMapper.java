package com.stock.supplier_service.mapper;

import com.stock.supplier_service.dto.request.SupplierProductRequest;
import com.stock.supplier_service.entity.SupplierProduct;
import com.stock.supplier_service.dto.response.SupplierProductResponse;

public class SupplierProductMapper {

    public static SupplierProduct toEntity(SupplierProductRequest request) {
        return SupplierProduct.builder()
                .supplierId(request.getSupplierId())
                .reference(request.getReference())
                .name(request.getName())
                .availableQuantity(request.getAvailableQuantity())
                .supplierPrice(request.getSupplierPrice())
                .build();
    }

    public static SupplierProductResponse toResponse(SupplierProduct entity) {
        return SupplierProductResponse.builder()
                .id(entity.getId())
                .supplierId(entity.getSupplierId())
                .reference(entity.getReference())
                .name(entity.getName())
                .availableQuantity(entity.getAvailableQuantity())
                .supplierPrice(entity.getSupplierPrice())
                .build();
    }
}