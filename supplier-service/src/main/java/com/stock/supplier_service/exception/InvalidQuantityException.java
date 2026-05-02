package com.stock.supplier_service.exception;

public class InvalidQuantityException extends BusinessException {
    public InvalidQuantityException(String message) {
        super(message);
    }
}