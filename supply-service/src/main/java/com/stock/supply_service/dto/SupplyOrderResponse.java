package com.stock.supply_service.dto;

import com.stock.supply_service.entity.SupplyStatus;

import java.time.LocalDate;
import java.util.List;

public class SupplyOrderResponse {

    private Long id;
    private Long supplierId;
    private LocalDate date;
    private SupplyStatus status;
    private List<SupplyItemResponse> items;

    public SupplyOrderResponse() {
    }

    public SupplyOrderResponse(Long id, Long supplierId, LocalDate date, SupplyStatus status, List<SupplyItemResponse> items) {
        this.id = id;
        this.supplierId = supplierId;
        this.date = date;
        this.status = status;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public LocalDate getDate() {
        return date;
    }

    public SupplyStatus getStatus() {
        return status;
    }

    public List<SupplyItemResponse> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(SupplyStatus status) {
        this.status = status;
    }

    public void setItems(List<SupplyItemResponse> items) {
        this.items = items;
    }
}