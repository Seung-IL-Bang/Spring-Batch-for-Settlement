package com.project.settlement_batch.job.total;

import com.project.settlement_batch.domain.entity.settlement.SettlementTotal;
import com.project.settlement_batch.domain.projection.SummingSettlementResponse;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class TotalSettlementItemProcessor implements ItemProcessor<SummingSettlementResponse, SettlementTotal> {
    @Override
    public SettlementTotal process(SummingSettlementResponse item) throws Exception {
        return SettlementTotal.builder()
                .settlementDate(LocalDate.now())
                .sellerId(item.getSellerId())
                .sellerName(item.getSellerName())
                .sellerBusinessNo(item.getSellerBusinessNo())
                .taxType(item.getTaxType())
                .sellType(item.getSellType())
                .pgSalesAmount(item.getSumPgSalesAmount())
                .couponDiscountAmount(item.getSumCouponDiscountAmount())
                .mileageUsedAmount(item.getSumMileageUsageAmount())
                .shippingFee(item.getSumShippingFeeAmount())
                .claimShippingFee(item.getSumClaimShippingFeeAmount())
                .taxAmount(item.getSumTaxAmount())
                .build();
    }
}
