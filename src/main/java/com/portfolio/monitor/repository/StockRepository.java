package com.portfolio.monitor.repository;

import com.portfolio.monitor.model.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, String> {
}
