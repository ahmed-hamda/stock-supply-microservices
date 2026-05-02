package com.stock.supplier_service.controller;

import com.stock.supplier_service.service.impl.SupplierProductServiceImpl;
import com.stock.supplier_service.dto.request.SupplierProductRequest;
import com.stock.supplier_service.dto.response.SupplierProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/supplier-products")
@RequiredArgsConstructor
@Tag(name = "Supplier Product API", description = "Operations related to supplier products")
public class SupplierProductController {

    private final SupplierProductServiceImpl service;

    @Operation(summary = "Create supplier product")
    @PostMapping
    public ResponseEntity<SupplierProductResponse> create(
            @Valid @RequestBody SupplierProductRequest request) {


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    // 🔥 BONUS
    @GetMapping("/by-supplier/{supplierId}")
    public ResponseEntity<List<SupplierProductResponse>> getBySupplier(
            @PathVariable UUID supplierId) {

        return ResponseEntity.ok(service.getBySupplier(supplierId));
    }

    @GetMapping("/by-reference/{reference}")
    public ResponseEntity<SupplierProductResponse> getByReference(
            @PathVariable String reference) {

        return ResponseEntity.ok(service.getByReference(reference));
    }
}