package com.project.settlement_batch.domain.entity.settlement.calculator;

import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;

import java.math.BigDecimal;

public class PgSalesAmountCalculator {

    private final OrderItemSnapshot orderItemSnapshot;

    public PgSalesAmountCalculator(OrderItemSnapshot orderItemSnapshot) {
        this.orderItemSnapshot = orderItemSnapshot;
    }

    public BigDecimal calculatePgSaleAmount() {
        BigDecimal sellPrice = orderItemSnapshot.getSellPrice();
        return sellPrice.subtract(orderItemSnapshot.getPromotionAmount())
                .subtract(orderItemSnapshot.getMileageUsedAmount());
    }
}
