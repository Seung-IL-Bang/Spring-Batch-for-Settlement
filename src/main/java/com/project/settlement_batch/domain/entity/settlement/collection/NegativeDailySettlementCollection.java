package com.project.settlement_batch.domain.entity.settlement.collection;

import com.project.settlement_batch.domain.entity.Seller;
import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;
import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;
import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import com.project.settlement_batch.domain.entity.settlement.calculator.ClaimShippedAmountCalculator;
import com.project.settlement_batch.domain.entity.settlement.calculator.CommisisonAmountCalculator;
import com.project.settlement_batch.domain.entity.settlement.calculator.PgSalesAmountCalculator;
import com.project.settlement_batch.domain.entity.settlement.calculator.TaxCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NegativeDailySettlementCollection {

    private final ClaimReceiptItem claimReceiptItem;

    public NegativeDailySettlementCollection(ClaimReceiptItem claimReceiptItem) {
        this.claimReceiptItem = claimReceiptItem;
    }

    public SettlementDaily getSettlementDaily() {

        OrderItem orderItem = claimReceiptItem.getOrderItem();
        OrderItemSnapshot orderItemSnapshot = orderItem.getOrderItemSnapshot();
        Seller seller = orderItemSnapshot.getSeller();

        int count = claimReceiptItem.getClaimItemCount();
        BigDecimal countToDecimal = BigDecimal.valueOf(count);

        // 세금 계산
        TaxCalculator taxCalculator = new TaxCalculator(orderItemSnapshot);
        BigDecimal taxAmount = taxCalculator.getTaxAmount().multiply(countToDecimal);

        // - 정산 금액에 필요한 데이터 만들기
        PgSalesAmountCalculator pgCalculator = new PgSalesAmountCalculator(orderItemSnapshot);
        BigDecimal pgSalesAmount = pgCalculator.calculatePgSaleAmount().multiply(countToDecimal);

        // 수수료 계산
        CommisisonAmountCalculator commisisonAmountCalculator = new CommisisonAmountCalculator(orderItemSnapshot);
        BigDecimal commissionAmount = commisisonAmountCalculator.getCommissionAmount().multiply(countToDecimal);

        ClaimShippedAmountCalculator claimShippedAmountCalculator = new ClaimShippedAmountCalculator(claimReceiptItem);
        BigDecimal claimShippingFeeAmount = claimShippedAmountCalculator.getClaimShippedAmount();




        return SettlementDaily.builder()
                .settlementDate(LocalDate.now())
                .orderId(orderItem.getOrderId())
                .orderItemCount(count)
                .orderItemId(orderItem.getId())
                .sellerId(orderItemSnapshot.getSeller().getId())
                .sellerBusinessNo(seller.getBusinessNo())
                .sellerName(seller.getSellerName())
                .sellType(seller.getSellType())
                .taxType(orderItemSnapshot.getTaxType())
                .taxAmount(taxAmount)
                .commissionAmount(commissionAmount)
                .pgSalesAmount(pgSalesAmount)
                .couponDiscountAmount(orderItemSnapshot.getPromotionAmount())
                .mileageUsedAmount(orderItemSnapshot.getMileageUsedAmount())
                .shippingFee(orderItemSnapshot.getDefaultDeliveryAmount())
                .claimReceiptId(claimReceiptItem.getClaimReceipt().getClaimReceiptId())
                .claimShippingFee()
                .build();
    }
}
