package com.stock.supply_service.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supply_orders")
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private SupplyStatus status;

    @OneToMany(mappedBy = "supplyOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplyItem> items = new ArrayList<>();

    public SupplyOrder() {
    }

    public SupplyOrder(Long id, Long supplierId, LocalDate date, SupplyStatus status) {
        this.id = id;
        this.supplierId = supplierId;
        this.date = date;
        this.status = status;
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

    public List<SupplyItem> getItems() {
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

    public void setItems(List<SupplyItem> items) {
        this.items = items;
    }
}