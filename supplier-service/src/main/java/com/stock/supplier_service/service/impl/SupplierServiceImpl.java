package com.stock.supplier_service.service.impl;

import com.stock.supplier_service.exception.DuplicateResourceException;
import com.stock.supplier_service.exception.ResourceNotFoundException;
import com.stock.supplier_service.repository.SupplierRepository;
import com.stock.supplier_service.service.SupplierService;
import com.stock.supplier_service.dto.request.SupplierRequest;
import com.stock.supplier_service.dto.response.SupplierResponse;
import com.stock.supplier_service.entity.Supplier;
import com.stock.supplier_service.mapper.SupplierMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {

         repository.findByEmail(request.getEmail())
                .ifPresent(s -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        Supplier supplier = SupplierMapper.toEntity(request);

        return SupplierMapper.toResponse(repository.save(supplier));
    }

    @Override
    public SupplierResponse getSupplierById(UUID id) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        return SupplierMapper.toResponse(supplier);
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        return repository.findAll()
                .stream()
                .map(SupplierMapper::toResponse)
                .toList();
    }

    @Override
    public Page<SupplierResponse> getAllSuppliers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(SupplierMapper::toResponse);
    }
    @Override
    public SupplierResponse findByEmail(String email) {

        Supplier supplier = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with email"));

        return SupplierMapper.toResponse(supplier);
    }


    @Override
    public void deleteSupplier(UUID id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found");
        }

        repository.deleteById(id);
    }
}