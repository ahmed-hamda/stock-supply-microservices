package com.stock.supply_service.external;

public class SupplierProductResponse {

    private Long id;
    private Long supplierId;
    private String name;
    private String reference;
    private Integer availableQuantity;
    private Double supplierPrice;

    public Long getId() { return id; }
    public Long getSupplierId() { return supplierId; }
    public String getName() { return name; }
    public String getReference() { return reference; }
    public Integer getAvailableQuantity() { return availableQuantity; }
    public Double getSupplierPrice() { return supplierPrice; }

    public void setId(Long id) { this.id = id; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public void setName(String name) { this.name = name; }
    public void setReference(String reference) { this.reference = reference; }
    public void setAvailableQuantity(Integer availableQuantity) { this.availableQuantity = availableQuantity; }
    public void setSupplierPrice(Double supplierPrice) { this.supplierPrice = supplierPrice; }
}