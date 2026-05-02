package com.stock.product_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String reference;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    @Min(0)
    private Integer minStock;

    @NotNull
    @Min(0)
    private Double sellingPrice;

    // Getters & Setters
    public String getName() { return name; }

    public String getReference() { return reference; }

    public Integer getQuantity() { return quantity; }

    public Integer getMinStock() { return minStock; }

    public Double getSellingPrice() { return sellingPrice; }

    public void setName(String name) { this.name = name; }

    public void setReference(String reference) { this.reference = reference; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void setMinStock(Integer minStock) { this.minStock = minStock; }

    public void setSellingPrice(Double sellingPrice) { this.sellingPrice = sellingPrice; }
}