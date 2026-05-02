package com.stock.supply_service.dto;

import jakarta.validation.constraints.NotNull;

public class SupplyOrderRequest {

    @NotNull
    private Long supplierId;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}