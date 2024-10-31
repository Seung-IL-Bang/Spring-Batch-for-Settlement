package com.project.settlement_batch.domain.entity.claim;

import com.project.settlement_batch.domain.constant.ClaimRequestType;
import com.project.settlement_batch.domain.constant.ClaimStatus;
import com.project.settlement_batch.domain.constant.ExtraFeePayer;
import com.project.settlement_batch.domain.converter.ClaimStatusConverter;
import com.project.settlement_batch.domain.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClaimReceipt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_receipt_id")
    private int claimReceiptId;

    private int orderId;

    @Enumerated(EnumType.STRING)
    private ClaimRequestType claimRequestType;

    @Convert(converter = ClaimStatusConverter.class)
    private ClaimStatus claimStatus;

    @Enumerated(EnumType.STRING)
    private ExtraFeePayer extraFeePayer;

    private String claimReason;

    private LocalDateTime completedAt;

    public void completedAtNow() {
        completedAt = LocalDateTime.now();
        claimStatus = ClaimStatus.COMPLETED;
    }

}
