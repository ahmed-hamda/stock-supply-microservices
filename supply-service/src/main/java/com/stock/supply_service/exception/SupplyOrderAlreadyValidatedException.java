package com.stock.supply_service.exception;

public class SupplyOrderAlreadyValidatedException extends RuntimeException {
    public SupplyOrderAlreadyValidatedException(Long id) {
        super("La commande d'approvisionnement est déjà validée : " + id);
    }
}