package com.stock.supply_service.exception;

public class InvalidQuantityException extends RuntimeException {

    private final String message;

    public InvalidQuantityException() {
        this("La quantité doit être strictement positive");
    }

    public InvalidQuantityException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}