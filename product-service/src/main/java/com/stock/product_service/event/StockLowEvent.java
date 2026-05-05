package com.stock.product_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockLowEvent {

    private Long productId;
    private String reference;
    private Integer currentQuantity;
    private Integer minStock;

}
