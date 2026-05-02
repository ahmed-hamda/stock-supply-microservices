package com.stock.supplier_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class SupplierProductRequest {

    @NotNull
    private UUID supplierId;

    @NotBlank
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "Invalid reference format")
    private String reference;

    @NotBlank
    private String name;

    @Positive
    private Integer availableQuantity;

    @Positive
    private Double supplierPrice;
}