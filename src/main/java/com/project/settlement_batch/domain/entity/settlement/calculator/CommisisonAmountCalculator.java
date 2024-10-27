package com.project.settlement_batch.domain.entity.settlement.calculator;

import com.project.settlement_batch.domain.entity.Seller;
import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;

import java.math.BigDecimal;

public class CommisisonAmountCalculator {

    private final OrderItemSnapshot orderItemSnapshot;

    public CommisisonAmountCalculator(OrderItemSnapshot orderItemSnapshot) {
        this.orderItemSnapshot = orderItemSnapshot;
    }

    public BigDecimal getCommissionAmount() {
        Seller seller = orderItemSnapshot.getSeller();
        BigDecimal sellPrice = orderItemSnapshot.getSellPrice();

        BigDecimal marginAmount = sellPrice.subtract(orderItemSnapshot.getSupplyPrice());
        int commission = seller.getCommission();

        return calculate(marginAmount, commission);
    }

    private BigDecimal calculate(BigDecimal marginAmount, int commission) {

        if (commission == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal rate = BigDecimal.valueOf(commission / 100);

        return marginAmount.multiply(rate);
    }
}
