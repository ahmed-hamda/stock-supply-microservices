package com.stock.supply_service.exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Long id) {
        super("Supplier not found with id: " + id);
    }
}
