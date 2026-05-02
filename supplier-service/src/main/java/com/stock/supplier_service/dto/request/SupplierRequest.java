package com.stock.supplier_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplierRequest {

    @NotBlank
    private String name;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "^[0-9+]{8,15}$", message = "Invalid phone number")
    private String phone;

    @Email
    private String email;
}