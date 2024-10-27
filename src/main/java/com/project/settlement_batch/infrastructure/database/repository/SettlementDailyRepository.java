package com.project.settlement_batch.infrastructure.database.repository;

import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementDailyRepository extends JpaRepository<SettlementDaily, Integer> {
}
