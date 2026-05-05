package com.stock.supply_service.event;

public class StockLowEvent {

    private Long productId;
    private String reference;
    private Integer currentQuantity;
    private Integer minStock;

    public StockLowEvent() {}

    public Long getProductId() {
        return productId;
    }

    public String getReference() {
        return reference;
    }

    public Integer getCurrentQuantity() {
        return currentQuantity;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setCurrentQuantity(Integer currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }
}