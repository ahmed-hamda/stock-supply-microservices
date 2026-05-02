package com.stock.supplier_service.service.impl;

import com.stock.supplier_service.exception.DuplicateResourceException;
import com.stock.supplier_service.exception.InvalidQuantityException;
import com.stock.supplier_service.exception.ResourceNotFoundException;
import com.stock.supplier_service.repository.SupplierProductRepository;
import com.stock.supplier_service.repository.SupplierRepository;
import com.stock.supplier_service.dto.request.SupplierProductRequest;
import com.stock.supplier_service.dto.response.SupplierProductResponse;
import com.stock.supplier_service.entity.SupplierProduct;
 import com.stock.supplier_service.mapper.SupplierProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.stock.supplier_service.service.SupplierProductService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository repository;
    private final SupplierRepository supplierRepository;

    public SupplierProductResponse create(SupplierProductRequest request) {

        // RULE: supplier must exist
        if (!supplierRepository.existsById(request.getSupplierId())) {
            throw new ResourceNotFoundException("Supplier not found");
        }

        // RULE: reference unique
        repository.findByReference(request.getReference())
                .ifPresent(p -> {
                    throw new DuplicateResourceException("Reference already exists");
                });

        // RULE: stock >= 0
        if (request.getAvailableQuantity() < 0) {
            throw new InvalidQuantityException("Quantity must be positive");
        }

        SupplierProduct entity = SupplierProductMapper.toEntity(request);

        return SupplierProductMapper.toResponse(repository.save(entity));
    }

    @Override
    public SupplierProductResponse getByReference(String reference) {

        SupplierProduct entity = repository.findByReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return SupplierProductMapper.toResponse(entity);
    }


    @Override
    public List<SupplierProductResponse> getBySupplier(UUID supplierId) {

        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("Supplier not found");
        }

        return repository.findBySupplierId(supplierId)
                .stream()
                .map(SupplierProductMapper::toResponse)
                .toList();
    }

}