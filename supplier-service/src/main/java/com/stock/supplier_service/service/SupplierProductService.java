package com.stock.supplier_service.service;

import com.stock.supplier_service.dto.response.SupplierProductResponse;

import java.util.List;

public interface SupplierProductService {

    SupplierProductResponse getByReference(String reference);

    List<SupplierProductResponse> getBySupplier(Long supplierId);

    SupplierProductResponse getCheapestByReference(String reference);
}