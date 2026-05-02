package com.stock.supply_service.client;

import com.stock.supply_service.external.SupplierProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "SUPPLIER-SERVICE", url = "${services.supplier.url}")
public interface SupplierClient {

    @GetMapping("/api/v1/supplier-products/{id}")
    SupplierProductResponse getSupplierProductById(@PathVariable Long id);

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
}