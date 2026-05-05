package com.stock.product_service.kafka;

import com.stock.product_service.event.StockLowEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockEventProducer {

    private static final String TOPIC = "stock-low-topic";

    private final KafkaTemplate<String, StockLowEvent> kafkaTemplate;

    public StockEventProducer(KafkaTemplate<String, StockLowEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishStockLowEvent(StockLowEvent event) {
        kafkaTemplate.send(TOPIC, event.getReference(), event);
        System.out.println("StockLowEvent envoyé vers Kafka : " + event.getReference());
    }
}