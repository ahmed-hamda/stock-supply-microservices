package com.stock.supply_service.client;

import com.stock.supply_service.external.SupplierProductResponse;
import com.stock.supply_service.external.SupplierResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "SUPPLIER-SERVICE")
public interface SupplierClient {

    @GetMapping("/api/v1/supplier-products/{id}")
    SupplierProductResponse getSupplierProductById(@PathVariable Long id);

    @GetMapping("/api/v1/suppliers/{id}")
    SupplierResponse getSupplierById(@PathVariable Long id);

    @GetMapping("/api/v1/supplier-products/supplier/{supplierId}/reference/{reference}")
    SupplierProductResponse getSupplierProductBySupplierAndReference(
            @PathVariable Long supplierId,
            @PathVariable String reference
    );

    @RequestMapping(method = RequestMethod.PATCH, value = "/api/v1/supplier-products/{id}/decrease-stock")
    SupplierProductResponse decreaseSupplierStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    );

    @GetMapping("/api/v1/supplier-products/reference/{reference}/cheapest")
    SupplierProductResponse getCheapestSupplierProductByReference(
            @PathVariable String reference
    );

}