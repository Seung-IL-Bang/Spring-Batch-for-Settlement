package com.project.settlement_batch.domain.entity.settlement.calculator;

import com.project.settlement_batch.domain.constant.Category;
import com.project.settlement_batch.domain.constant.TaxType;
import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;

import java.math.BigDecimal;

public class TaxCalculator {

    private final OrderItemSnapshot orderItemSnapshot;

    private final BigDecimal TAX_RATE = BigDecimal.valueOf(0.03);

    public TaxCalculator(OrderItemSnapshot orderItemSnapshot) {
        this.orderItemSnapshot = orderItemSnapshot;
    }

    public BigDecimal getTaxAmount() {

        TaxType taxType = orderItemSnapshot.getTaxType();

        if (taxType == TaxType.TAXABLE) {
            return calculateTaxByItemCategory();
        } else if (taxType == TaxType.ZERO) {
            return BigDecimal.ZERO;
        } else if (taxType == TaxType.FREE) {
            return BigDecimal.ZERO;
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal calculateTaxByItemCategory() {
        BigDecimal sellPrice = orderItemSnapshot.getSellPrice();
        Category category = orderItemSnapshot.getCategory();

        if (category == Category.ITEM_ONE) {
            return sellPrice.multiply(BigDecimal.valueOf(0.20));
        }

        return sellPrice.multiply(TAX_RATE);
    }


}
