package com.stock.supplier_service.service;

import com.stock.supplier_service.dto.request.SupplierRequest;
import com.stock.supplier_service.dto.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse getSupplierById(Long id);

    List<SupplierResponse> getAllSuppliers();
    Page<SupplierResponse> getAllSuppliers(Pageable pageable);


    void deleteSupplier(Long id);
    SupplierResponse findByEmail(String email);
}