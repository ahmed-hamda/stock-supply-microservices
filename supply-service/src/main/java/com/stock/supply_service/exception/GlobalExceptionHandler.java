package com.stock.supply_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SupplyOrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSupplyOrderNotFound(SupplyOrderNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), "SUPPLY_ORDER_NOT_FOUND", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSupplierNotFound(SupplierNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), "SUPPLIER__NOT_FOUND", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(SupplyOrderAlreadyValidatedException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyValidated(SupplyOrderAlreadyValidatedException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.CONFLICT.value(), "SUPPLY_ORDER_ALREADY_VALIDATED", ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(SupplyOrderAlreadyReceivedException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyReceived(SupplyOrderAlreadyReceivedException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.CONFLICT.value(), "SUPPLY_ORDER_ALREADY_RECEIVED", ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidQuantity(InvalidQuantityException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "INVALID_QUANTITY", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InsufficientSupplierStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStock(InsufficientSupplierStockException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "INSUFFICIENT_SUPPLIER_STOCK", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .orElse("Erreur de validation");

        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR", message),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}