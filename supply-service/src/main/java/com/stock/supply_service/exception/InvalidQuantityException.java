package com.stock.supply_service.exception;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("La quantité doit être strictement positive");
    }
}