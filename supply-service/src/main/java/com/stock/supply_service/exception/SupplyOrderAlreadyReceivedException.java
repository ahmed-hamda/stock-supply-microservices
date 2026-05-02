package com.stock.supply_service.exception;

public class SupplyOrderAlreadyReceivedException extends RuntimeException {
    public SupplyOrderAlreadyReceivedException(Long id) {
        super("La commande d'approvisionnement est déjà reçue : " + id);
    }
}