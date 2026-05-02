package com.stock.supply_service.mapper;

import com.stock.supply_service.dto.SupplyItemResponse;
import com.stock.supply_service.dto.SupplyOrderResponse;
import com.stock.supply_service.entity.SupplyItem;
import com.stock.supply_service.entity.SupplyOrder;

import java.util.List;

public class SupplyMapper {

    public static SupplyItemResponse toItemResponse(SupplyItem item) {
        return new SupplyItemResponse(
                item.getId(),
                item.getProductId(),
                item.getSupplierProductId(),
                item.getReference(),
                item.getQuantity()
        );
    }

    public static SupplyOrderResponse toOrderResponse(SupplyOrder order) {
        List<SupplyItemResponse> itemResponses = order.getItems()
                .stream()
                .map(SupplyMapper::toItemResponse)
                .toList();

        return new SupplyOrderResponse(
                order.getId(),
                order.getSupplierId(),
                order.getDate(),
                order.getStatus(),
                itemResponses
        );
    }
}