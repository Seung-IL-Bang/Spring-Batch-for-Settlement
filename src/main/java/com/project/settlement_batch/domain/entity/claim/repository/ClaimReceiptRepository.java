package com.project.settlement_batch.domain.entity.claim.repository;

import com.project.settlement_batch.domain.entity.claim.ClaimReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimReceiptRepository extends JpaRepository<ClaimReceipt, Integer> {
}
