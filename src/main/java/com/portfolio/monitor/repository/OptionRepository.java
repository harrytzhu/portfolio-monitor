package com.portfolio.monitor.repository;

import com.portfolio.monitor.model.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, String> {
}
