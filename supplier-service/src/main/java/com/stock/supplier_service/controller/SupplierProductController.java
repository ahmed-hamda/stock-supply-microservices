package com.stock.supplier_service.controller;

import com.stock.supplier_service.dto.request.SupplierProductRequest;
import com.stock.supplier_service.dto.response.ErrorResponse;
import com.stock.supplier_service.dto.response.SupplierProductResponse;
import com.stock.supplier_service.service.impl.SupplierProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // CREATE
    @Operation(summary = "Create supplier product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Supplier product created successfully",
                    content = @Content(schema = @Schema(implementation = SupplierProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Duplicate reference for supplier",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<SupplierProductResponse> create(
            @Valid @RequestBody SupplierProductRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    // GET BY ID
    @Operation(summary = "Get supplier product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier product found",
                    content = @Content(schema = @Schema(implementation = SupplierProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supplier product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProductResponse> getById(
            @Parameter(description = "Supplier Product ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    // GET BY SUPPLIER
    @Operation(summary = "Get supplier products by supplier ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier products retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/by-supplier/{supplierId}")
    public ResponseEntity<List<SupplierProductResponse>> getBySupplier(
            @Parameter(description = "Supplier ID", example = "1")
            @PathVariable Long supplierId) {

        return ResponseEntity.ok(service.getBySupplier(supplierId));
    }

    // GET BY REFERENCE
    @Operation(summary = "Get supplier product by reference")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier product found",
                    content = @Content(schema = @Schema(implementation = SupplierProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supplier product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/by-reference/{reference}")
    public ResponseEntity<SupplierProductResponse> getByReference(
            @Parameter(description = "Product reference", example = "hp002")
            @PathVariable String reference) {

        return ResponseEntity.ok(service.getByReference(reference));
    }

    // GET BY SUPPLIER + REFERENCE
    @Operation(summary = "Get supplier product by supplier ID and reference")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier product found",
                    content = @Content(schema = @Schema(implementation = SupplierProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supplier product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/supplier/{supplierId}/reference/{reference}")
    public ResponseEntity<SupplierProductResponse> getBySupplierAndReference(
            @Parameter(description = "Supplier ID", example = "1")
            @PathVariable Long supplierId,
            @Parameter(description = "Product reference", example = "hp002")
            @PathVariable String reference) {

        return ResponseEntity.ok(service.getBySupplierAndReference(supplierId, reference));
    }

    // DECREASE STOCK
    @Operation(summary = "Decrease supplier product stock")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock decreased successfully",
                    content = @Content(schema = @Schema(implementation = SupplierProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid quantity or insufficient stock",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supplier product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}/decrease-stock")
    public ResponseEntity<SupplierProductResponse> decreaseStock(
            @Parameter(description = "Supplier Product ID", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Quantity to decrease", example = "5")
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(service.decreaseStock(id, quantity));
    }
}