package com.project.settlement_batch.domain.projection;

import com.project.settlement_batch.domain.constant.SellType;
import com.project.settlement_batch.domain.constant.TaxType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummingSettlementResponse {

    private Integer sellerId;
    private String sellerName;
    private String sellerBusinessNo;
    private TaxType taxType;
    private SellType sellType;
    private BigDecimal sumPgSalesAmount;
    private BigDecimal sumCouponDiscountAmount;
    private BigDecimal sumMileageUsageAmount;
    private BigDecimal sumShippingFeeAmount;
    private BigDecimal sumClaimShippingFeeAmount;
    private BigDecimal sumCommissionAmount;
    private BigDecimal sumTaxAmount;
}
