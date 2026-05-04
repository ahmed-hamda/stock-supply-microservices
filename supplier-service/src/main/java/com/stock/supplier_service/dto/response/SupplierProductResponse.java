package com.stock.supplier_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierProductResponse {

    private Long id;
    private Long supplierId;
    private String reference;
    private String name;
    private Integer availableQuantity;
    private Double supplierPrice;
}