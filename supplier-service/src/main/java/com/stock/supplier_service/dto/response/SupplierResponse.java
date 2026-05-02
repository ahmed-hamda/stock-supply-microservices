package com.stock.supplier_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierResponse {

    private Long id;
    private String name;
    private String phone;
    private String email;
}