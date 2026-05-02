package com.stock.supplier_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplierRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 8, max = 20)
    @Pattern(regexp = "^[0-9+]{8,15}$", message = "Invalid phone number")
    private String phone;

    @Email
    private String email;
}