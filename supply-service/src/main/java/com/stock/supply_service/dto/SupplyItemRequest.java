package com.stock.supply_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SupplyItemRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long supplierProductId;

    @NotBlank
    private String reference;

    @NotNull
    @Min(1)
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public Long getSupplierProductId() {
        return supplierProductId;
    }

    public String getReference() {
        return reference;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setSupplierProductId(Long supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}