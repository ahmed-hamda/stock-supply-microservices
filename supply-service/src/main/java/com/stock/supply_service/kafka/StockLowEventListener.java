package com.stock.supply_service.kafka;

import com.stock.supply_service.event.StockLowEvent;
import com.stock.supply_service.service.SupplyOrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockLowEventListener {

    private final SupplyOrderService supplyOrderService;

    public StockLowEventListener(SupplyOrderService supplyOrderService) {
        this.supplyOrderService = supplyOrderService;
    }

    @KafkaListener(topics = "stock-low-topic", groupId = "supply-service-group")
    public void consumeStockLowEvent(StockLowEvent event) {

        System.out.println("StockLowEvent reçu : " + event.getReference());

        supplyOrderService.createAutomaticOrderFromStockLow(
                event.getProductId(),
                event.getReference(),
                event.getCurrentQuantity(),
                event.getMinStock()
        );
    }
}