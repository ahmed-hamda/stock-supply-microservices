package com.stock.supply_service.repository;

import com.stock.supply_service.entity.SupplyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
}