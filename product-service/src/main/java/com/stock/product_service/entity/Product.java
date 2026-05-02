package com.stock.product_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
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

    // Constructors
    public Product() {}

    public Product(Long id, String name, String reference, Integer quantity, Integer minStock, Double sellingPrice) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.quantity = quantity;
        this.minStock = minStock;
        this.sellingPrice = sellingPrice;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }

    public String getReference() { return reference; }

    public Integer getQuantity() { return quantity; }

    public Integer getMinStock() { return minStock; }

    public Double getSellingPrice() { return sellingPrice; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setReference(String reference) { this.reference = reference; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void setMinStock(Integer minStock) { this.minStock = minStock; }

    public void setSellingPrice(Double sellingPrice) { this.sellingPrice = sellingPrice; }
}