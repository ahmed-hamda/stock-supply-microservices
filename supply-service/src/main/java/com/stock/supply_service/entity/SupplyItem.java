package com.stock.supply_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "supply_items")
public class SupplyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long supplierProductId;

    private String reference;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "supply_order_id")
    private SupplyOrder supplyOrder;

    public SupplyItem() {
    }

    public SupplyItem(Long id, Long productId, Long supplierProductId, String reference, Integer quantity, SupplyOrder supplyOrder) {
        this.id = id;
        this.productId = productId;
        this.supplierProductId = supplierProductId;
        this.reference = reference;
        this.quantity = quantity;
        this.supplyOrder = supplyOrder;
    }

    public Long getId() {
        return id;
    }

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

    public SupplyOrder getSupplyOrder() {
        return supplyOrder;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setSupplyOrder(SupplyOrder supplyOrder) {
        this.supplyOrder = supplyOrder;
    }
}