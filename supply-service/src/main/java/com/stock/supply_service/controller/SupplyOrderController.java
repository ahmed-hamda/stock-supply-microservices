package com.stock.supply_service.controller;

import com.stock.supply_service.dto.SupplyItemRequest;
import com.stock.supply_service.dto.SupplyOrderRequest;
import com.stock.supply_service.dto.SupplyOrderResponse;
import com.stock.supply_service.service.SupplyOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supply-orders")
@RequiredArgsConstructor
@Tag(name = "Supply Order API", description = "Operations related to supply order management")
@SecurityRequirement(name = "basicAuth")
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;

    @Operation(summary = "Create a new supply order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supply order created",
                    content = @Content(schema = @Schema(implementation = SupplyOrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<SupplyOrderResponse> createOrder(
            @Valid @RequestBody SupplyOrderRequest request) {

        SupplyOrderResponse response = supplyOrderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all supply orders")
    @GetMapping
    public ResponseEntity<List<SupplyOrderResponse>> getAllOrders() {
        return ResponseEntity.ok(supplyOrderService.getAllOrders());
    }

    @Operation(summary = "Get supply order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SupplyOrderResponse> getOrderById(
            @Parameter(description = "Order ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(supplyOrderService.getOrderById(id));
    }

    @Operation(summary = "Add item to supply order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item added successfully",
                    content = @Content(schema = @Schema(implementation = SupplyOrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PostMapping("/{id}/items")
    public ResponseEntity<SupplyOrderResponse> addItem(
            @Parameter(description = "Order ID", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody SupplyItemRequest request) {

        return ResponseEntity.ok(supplyOrderService.addItem(id, request));
    }

    @Operation(summary = "Validate supply order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order validated",
                    content = @Content(schema = @Schema(implementation = SupplyOrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid state")
    })
    @PostMapping("/{id}/validate")
    public ResponseEntity<SupplyOrderResponse> validateOrder(
            @Parameter(description = "Order ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(supplyOrderService.validateOrder(id));
    }

    @Operation(summary = "Receive supply order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order received and stock updated",
                    content = @Content(schema = @Schema(implementation = SupplyOrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid state")
    })
    @PostMapping("/{id}/receive")
    public ResponseEntity<SupplyOrderResponse> receiveOrder(
            @Parameter(description = "Order ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(supplyOrderService.receiveOrder(id));
    }

    @Operation(summary = "Cancel supply order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order cancelled"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(
            @Parameter(description = "Order ID", example = "1")
            @PathVariable Long id) {

        supplyOrderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

    // HEALTH CHECK
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Supply Order Service is UP");
    }
}