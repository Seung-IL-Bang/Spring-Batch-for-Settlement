package com.project.settlement_batch.domain.entity.claim;

import com.project.settlement_batch.domain.entity.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ClaimRefundHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_refund_history_id")
    private int id;

    private int sellerId;

    private int claimReceiptId;

    private LocalDateTime refundAt;

    private BigDecimal refundCashAmount = BigDecimal.ZERO;
    private BigDecimal couponDiscountAmount = BigDecimal.ZERO;
    private BigDecimal refundMileageAmount = BigDecimal.ZERO;
    private BigDecimal subtractDeliveryAmount = BigDecimal.ZERO;
    private BigDecimal refundDeliveryAmount = BigDecimal.ZERO;
}
