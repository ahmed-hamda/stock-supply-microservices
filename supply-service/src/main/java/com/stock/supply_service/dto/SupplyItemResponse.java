package com.stock.supply_service.dto;

public class SupplyItemResponse {

    private Long id;
    private Long productId;
    private Long supplierProductId;
    private String reference;
    private Integer quantity;

    public SupplyItemResponse() {
    }

    public SupplyItemResponse(Long id, Long productId, Long supplierProductId, String reference, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.supplierProductId = supplierProductId;
        this.reference = reference;
        this.quantity = quantity;
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
}