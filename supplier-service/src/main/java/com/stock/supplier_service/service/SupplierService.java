package com.stock.supplier_service.service;

import com.stock.supplier_service.dto.request.SupplierRequest;
import com.stock.supplier_service.dto.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse getSupplierById(UUID id);

    List<SupplierResponse> getAllSuppliers();
    Page<SupplierResponse> getAllSuppliers(Pageable pageable);


    void deleteSupplier(UUID id);
    SupplierResponse findByEmail(String email);
}