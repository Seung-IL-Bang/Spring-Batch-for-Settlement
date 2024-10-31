package com.project.settlement_batch.domain.entity.claim;

import com.project.settlement_batch.domain.entity.BaseEntity;
import com.project.settlement_batch.domain.entity.order.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class ClaimReceiptItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_receipt_item_id")
    private int claimReceiptItemId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "claim_receipt_id")
    private ClaimReceipt claimReceipt;

    private int claimItemCount;
}
