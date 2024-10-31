package com.project.settlement_batch.infrastructure.database.repository;

import com.project.settlement_batch.domain.entity.settlement.SettlementTotal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementTotalRepository extends JpaRepository<SettlementTotal, Integer> {
}
