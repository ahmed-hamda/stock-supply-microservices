package com.stock.supplier_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "supplier_products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SupplierProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID supplierId;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String name;

    private Integer availableQuantity;

    private Double supplierPrice;
}