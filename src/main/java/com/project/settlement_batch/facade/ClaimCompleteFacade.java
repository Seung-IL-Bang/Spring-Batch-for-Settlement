package com.project.settlement_batch.facade;

import com.project.settlement_batch.domain.entity.claim.service.ClaimCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimCompleteFacade {

    private final ClaimCompleteService claimCompleteService;

    public void receiptComplete(Integer claimReceiptId) {
        // 읽기 서비스
        // 쓰기 서비스
        claimCompleteService.complete(claimReceiptId);
    }
}
