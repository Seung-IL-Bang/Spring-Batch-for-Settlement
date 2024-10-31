package com.project.settlement_batch.domain.projection;

import com.project.settlement_batch.domain.constant.SellType;
import com.project.settlement_batch.domain.constant.TaxType;

import java.math.BigDecimal;

public class SummingSettlementResponse {

    private Long sellerId;
    private String sellerName;
    private String sellerBusinessNumber;
    private TaxType taxType;
    private SellType sellType;
    private BigDecimal sumPgSalesAmount;
    private BigDecimal sumCouponDiscountAmount;
    private BigDecimal sumMileageUsageAmount;
    private BigDecimal sumShippingFeeAmount;
    private BigDecimal sumClaimShippingFeeAmount;
    private BigDecimal sumCommissionAmount;
}
