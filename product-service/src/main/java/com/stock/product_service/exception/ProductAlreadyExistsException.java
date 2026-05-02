package com.stock.product_service.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String reference) {
        super("Un produit avec la référence '" + reference + "' existe déjà");
    }
}