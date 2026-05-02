package com.stock.supplier_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class SupplierProductRequest {

    @NotNull
    private Long supplierId;

    @NotBlank
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "Invalid reference format")
    private String reference;

    @NotBlank
    private String name;

    @Positive
    private Integer availableQuantity;

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setSupplierPrice(Double supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public String getReference() {
        return reference;
    }

    public String getName() {
        return name;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public Double getSupplierPrice() {
        return supplierPrice;
    }

    @Positive
    private Double supplierPrice;
}