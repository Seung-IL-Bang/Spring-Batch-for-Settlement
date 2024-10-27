package com.project.settlement_batch.domain.entity.settlement.collection;

import com.project.settlement_batch.domain.entity.Seller;
import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;
import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import com.project.settlement_batch.domain.entity.settlement.calculator.CommisisonAmountCalculator;
import com.project.settlement_batch.domain.entity.settlement.calculator.PgSalesAmountCalculator;
import com.project.settlement_batch.domain.entity.settlement.calculator.TaxCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PositiveDailySettlementCollection {

    private final OrderItem orderItem;

    public PositiveDailySettlementCollection(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public SettlementDaily getSettlementDaily() {
        OrderItemSnapshot orderItemSnapshot = orderItem.getOrderItemSnapshot();
        Seller seller = orderItemSnapshot.getSeller();
        int itemCount = orderItem.getItemCount();
        BigDecimal countToDecimal = BigDecimal.valueOf(itemCount);

        // 세금 계산
        TaxCalculator taxCalculator = new TaxCalculator(orderItemSnapshot);
        BigDecimal taxAmount = taxCalculator.getTaxAmount().multiply(countToDecimal);

        // + 정산 금액에 필요한 데이터 만들기
        PgSalesAmountCalculator pgCalculator = new PgSalesAmountCalculator(orderItemSnapshot);
        BigDecimal pgSalesAmount = pgCalculator.calculatePgSaleAmount().multiply(countToDecimal);

        // 수수료 계산
        CommisisonAmountCalculator commisisonAmountCalculator = new CommisisonAmountCalculator(orderItemSnapshot);
        BigDecimal commissionAmount = commisisonAmountCalculator.getCommissionAmount().multiply(countToDecimal);


        return SettlementDaily.builder()
                .settlementDate(LocalDate.now())
                .orderId(orderItem.getOrderId())
                .orderItemCount(itemCount)
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
                .build();
    }
}
