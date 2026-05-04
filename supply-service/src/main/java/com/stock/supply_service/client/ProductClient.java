package com.stock.supply_service.client;

import com.stock.supply_service.external.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE", url = "${services.product.url}")
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @GetMapping("/api/v1/products/reference/{reference}")
    ProductResponse getProductByReference(@PathVariable String reference);

    @RequestMapping(method = RequestMethod.PATCH, value = "/api/v1/products/{id}/increase-stock")
    ProductResponse increaseStock(
            @PathVariable Long id,
            @RequestParam Integer quantity
    );
}