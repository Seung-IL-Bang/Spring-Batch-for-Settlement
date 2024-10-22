package com.project.settlement_batch.domain.entity.claim.repository;

public interface ClaimCompleteExecutor {

    void updateCompletedAt(Integer claimReceiptId);
}
