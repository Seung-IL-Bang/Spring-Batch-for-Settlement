package com.project.settlement_batch.domain.entity.claim.service;

import com.project.settlement_batch.domain.entity.claim.repository.ClaimCompleteExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimCompleteService {

    private final ClaimCompleteExecutor claimCompleteExecutor;

    public void complete(Integer claimReceiptId) {
        claimCompleteExecutor.updateCompletedAt(claimReceiptId);
    }

}
