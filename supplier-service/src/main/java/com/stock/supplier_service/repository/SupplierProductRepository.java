package com.stock.supplier_service.repository;

import com.stock.supplier_service.entity.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {

    List<SupplierProduct> findBySupplierId(UUID supplierId);

    Optional<SupplierProduct> findByReference(String reference);
}