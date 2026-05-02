package com.stock.supply_service.exception;

public class InsufficientSupplierStockException extends RuntimeException {
    public InsufficientSupplierStockException(Long supplierProductId) {
        super("Stock fournisseur insuffisant pour le produit fournisseur id : " + supplierProductId);
    }
}