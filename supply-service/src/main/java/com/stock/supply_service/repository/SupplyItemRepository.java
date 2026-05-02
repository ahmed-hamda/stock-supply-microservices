package com.stock.supply_service.repository;

import com.stock.supply_service.entity.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyItemRepository extends JpaRepository<SupplyItem, Long> {

    List<SupplyItem> findBySupplyOrderId(Long supplyOrderId);
}