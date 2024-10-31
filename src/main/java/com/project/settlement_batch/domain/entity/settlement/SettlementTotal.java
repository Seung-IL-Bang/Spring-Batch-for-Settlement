package com.project.settlement_batch.domain.entity.settlement;

import com.project.settlement_batch.domain.constant.SellType;
import com.project.settlement_batch.domain.constant.TaxType;
import com.project.settlement_batch.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementTotal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_total_id")
    private int id;

    private int sellerId;

    @Enumerated(EnumType.STRING)
    private SellType sellType;

    private String sellerName;

    private String sellerBusinessNo;

    private int claimReceiptId;

    private LocalDate settlementDate;


    @Enumerated(EnumType.STRING)
    private TaxType taxType;

    private BigDecimal taxAmount = BigDecimal.ZERO;

    private BigDecimal pgSalesAmount = BigDecimal.ZERO;
    private BigDecimal couponDiscountAmount = BigDecimal.ZERO;
    private BigDecimal mileageUsedAmount = BigDecimal.ZERO;
    private BigDecimal shippingFee = BigDecimal.ZERO;
    private BigDecimal claimShippingFee = BigDecimal.ZERO;
    private BigDecimal commissionAmount = BigDecimal.ZERO;
}
