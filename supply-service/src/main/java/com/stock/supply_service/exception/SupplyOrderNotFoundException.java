package com.stock.supply_service.exception;

public class SupplyOrderNotFoundException extends RuntimeException {
    public SupplyOrderNotFoundException(Long id) {
        super("Commande d'approvisionnement introuvable avec id : " + id);
    }
}