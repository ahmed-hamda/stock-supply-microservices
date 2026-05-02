package com.stock.supplier_service.controller;

import com.stock.supplier_service.service.SupplierService;
import com.stock.supplier_service.dto.request.SupplierRequest;
import com.stock.supplier_service.dto.response.SupplierResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
@Tag(name = "Supplier API", description = "Operations related to suppliers management")
public class SupplierController {

    private final SupplierService service;

    @Operation(summary = "Create a new supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supplier created",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Duplicate email")
    })
    @PostMapping
    public ResponseEntity<SupplierResponse> create(
            @Valid @RequestBody SupplierRequest request,
            @RequestHeader(value = "X-Correlation-Id", required = false) String correlationId) {


        SupplierResponse response = service.createSupplier(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get supplier by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getById(
            @Parameter(description = "Supplier ID", example = "1")
            @PathVariable Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return ResponseEntity.ok(service.getSupplierById(id));
    }

    @Operation(summary = "Get all suppliers with pagination")
    @GetMapping
    public ResponseEntity<Page<SupplierResponse>> getAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return ResponseEntity.ok(service.getAllSuppliers(pageable));
    }

    @Operation(summary = "Delete supplier")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search suppliers by email")
    @GetMapping("/search")
    public ResponseEntity<SupplierResponse> findByEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(service.findByEmail(email));
    }

    // HEALTH CHECK
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Supplier Service is UP");
    }
}