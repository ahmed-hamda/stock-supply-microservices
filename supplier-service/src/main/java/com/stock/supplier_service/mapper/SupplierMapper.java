package com.stock.supplier_service.mapper;

import com.stock.supplier_service.dto.request.SupplierRequest;
import com.stock.supplier_service.dto.response.SupplierResponse;
import com.stock.supplier_service.entity.Supplier;

public class SupplierMapper {

    public static Supplier toEntity(SupplierRequest request) {
        return Supplier.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
    }

    public static SupplierResponse toResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .build();
    }
}