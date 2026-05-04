package com.stock.supplier_service.controller;

import com.stock.supplier_service.dto.response.ErrorResponse;
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

    // CREATE SUPPLIER
    @Operation(summary = "Create a new supplier")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Supplier created successfully",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Duplicate email",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<SupplierResponse> create(
            @Valid @RequestBody SupplierRequest request,
            @Parameter(description = "Correlation ID for tracing", example = "abc-123")
            @RequestHeader(value = "X-Correlation-Id", required = false) String correlationId) {

        SupplierResponse response = service.createSupplier(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET BY ID
    @Operation(summary = "Get supplier by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier found",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getById(
            @Parameter(description = "Supplier ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getSupplierById(id));
    }

    // GET ALL WITH PAGINATION
    @Operation(summary = "Get all suppliers with pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Suppliers retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<Page<SupplierResponse>> getAll(
            @Parameter(description = "Pagination parameters (page, size, sort)")
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return ResponseEntity.ok(service.getAllSuppliers(pageable));
    }

    // DELETE
    @Operation(summary = "Delete supplier by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Supplier ID", example = "1")
            @PathVariable Long id) {

        service.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    // SEARCH BY EMAIL
    @Operation(summary = "Find supplier by email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier found",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid email format",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<SupplierResponse> findByEmail(
            @Parameter(description = "Supplier email", example = "supplier@mail.com")
            @RequestParam String email) {

        return ResponseEntity.ok(service.findByEmail(email));
    }

    // HEALTH CHECK
    @Operation(summary = "Health check endpoint")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Service is up")
    })
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Supplier Service is UP");
    }
}