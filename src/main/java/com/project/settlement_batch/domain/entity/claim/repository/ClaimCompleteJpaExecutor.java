package com.project.settlement_batch.domain.entity.claim.repository;

import com.project.settlement_batch.domain.entity.claim.ClaimReceipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClaimCompleteJpaExecutor implements ClaimCompleteExecutor{

    private final ClaimReceiptRepository claimReceiptRepository;

    @Transactional
    @Override
    public void updateCompletedAt(Integer claimReceiptId) {
        ClaimReceipt claimReceipt = claimReceiptRepository
                .findById(claimReceiptId)
                .orElseThrow(NoSuchElementException::new);

        claimReceipt.completedAtNow(); // now() & ClaimStatus.COMPLETED
    }
}
