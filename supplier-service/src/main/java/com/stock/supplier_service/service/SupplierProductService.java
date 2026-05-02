package com.stock.supplier_service.service;

import com.stock.supplier_service.dto.response.SupplierProductResponse;

import java.util.List;
import java.util.UUID;

public interface SupplierProductService {

    SupplierProductResponse getByReference(String reference);

    List<SupplierProductResponse> getBySupplier(UUID supplierId);
}