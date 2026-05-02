package com.stock.supplier_service.controller;

import com.stock.supplier_service.dto.request.SupplierProductRequest;
import com.stock.supplier_service.dto.response.SupplierProductResponse;
import com.stock.supplier_service.service.impl.SupplierProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Get supplier product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Get supplier products by supplier ID")
    @GetMapping("/by-supplier/{supplierId}")
    public ResponseEntity<List<SupplierProductResponse>> getBySupplier(
            @PathVariable Long supplierId) {

        return ResponseEntity.ok(service.getBySupplier(supplierId));
    }

    @Operation(summary = "Get supplier product by reference")
    @GetMapping("/by-reference/{reference}")
    public ResponseEntity<SupplierProductResponse> getByReference(
            @PathVariable String reference) {

        return ResponseEntity.ok(service.getByReference(reference));
    }

    @Operation(summary = "Get supplier product by supplier ID and reference")
    @GetMapping("/supplier/{supplierId}/reference/{reference}")
    public ResponseEntity<SupplierProductResponse> getBySupplierAndReference(
            @PathVariable Long supplierId,
            @PathVariable String reference) {

        return ResponseEntity.ok(service.getBySupplierAndReference(supplierId, reference));
    }

    @Operation(summary = "Decrease supplier product stock")
    @PatchMapping("/{id}/decrease-stock")
    public ResponseEntity<SupplierProductResponse> decreaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(service.decreaseStock(id, quantity));
    }
}