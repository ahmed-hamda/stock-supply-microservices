package com.stock.supplier_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SupplierProductResponse {

    private Long id;
    private UUID supplierId;
    private String reference;
    private String name;
    private Integer availableQuantity;
    private Double supplierPrice;
}