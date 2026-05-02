package com.stock.supply_service.controller;

import com.stock.supply_service.dto.SupplyItemRequest;
import com.stock.supply_service.dto.SupplyOrderRequest;
import com.stock.supply_service.dto.SupplyOrderResponse;
import com.stock.supply_service.service.SupplyOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply-orders")
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;

    public SupplyOrderController(SupplyOrderService supplyOrderService) {
        this.supplyOrderService = supplyOrderService;
    }

    @PostMapping
    public SupplyOrderResponse createOrder(@Valid @RequestBody SupplyOrderRequest request) {
        return supplyOrderService.createOrder(request);
    }

    @GetMapping
    public List<SupplyOrderResponse> getAllOrders() {
        return supplyOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public SupplyOrderResponse getOrderById(@PathVariable Long id) {
        return supplyOrderService.getOrderById(id);
    }

    @PostMapping("/{id}/items")
    public SupplyOrderResponse addItem(
            @PathVariable Long id,
            @Valid @RequestBody SupplyItemRequest request
    ) {
        return supplyOrderService.addItem(id, request);
    }

    @PostMapping("/{id}/validate")
    public SupplyOrderResponse validateOrder(@PathVariable Long id) {
        return supplyOrderService.validateOrder(id);
    }

    @PostMapping("/{id}/receive")
    public SupplyOrderResponse receiveOrder(@PathVariable Long id) {
        return supplyOrderService.receiveOrder(id);
    }

    @PostMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable Long id) {
        supplyOrderService.cancelOrder(id);
    }
}