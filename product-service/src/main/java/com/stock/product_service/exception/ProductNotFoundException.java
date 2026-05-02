package com.stock.product_service.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Produit introuvable avec id : " + id);
    }

    public ProductNotFoundException(String reference) {
        super("Produit introuvable avec référence : " + reference);
    }
}