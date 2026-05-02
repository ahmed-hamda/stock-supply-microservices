package com.stock.product_service.exception;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException() {
        super("La quantité doit être strictement positive");
    }
}