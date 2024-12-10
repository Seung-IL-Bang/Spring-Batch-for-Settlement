package com.project.settlement_batch.domain.entity.settlement.calculator;

import com.project.settlement_batch.domain.constant.ClaimRequestType;
import com.project.settlement_batch.domain.constant.ExtraFeePayer;
import com.project.settlement_batch.domain.entity.claim.ClaimReceipt;
import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;

import java.math.BigDecimal;

public class ClaimShippedAmountCalculator {

    private static final BigDecimal SHIPPING_AMOUNT = BigDecimal.valueOf(3000L);

    private final ClaimReceiptItem claimReceiptItem;

    public ClaimShippedAmountCalculator(ClaimReceiptItem claimReceiptItem) {
        this.claimReceiptItem = claimReceiptItem;
    }

    public BigDecimal getClaimShippedAmount() {

        ClaimReceipt claimReceipt = claimReceiptItem.getClaimReceipt();
        int shippingCount = getShippingCount(claimReceipt.getClaimRequestType(), claimReceipt.getExtraFeePayer());

        return SHIPPING_AMOUNT.multiply(new BigDecimal(shippingCount));
    }

    private int getShippingCount(ClaimRequestType claimRequestType, ExtraFeePayer extraFeePayer) {

        if (extraFeePayer == ExtraFeePayer.CUSTOMER) {
            return 0;
        }

        switch (claimRequestType) {
            case CANCELLATION -> {
                return 0;
            }
            case EXCHANGE -> {
                return -2;
            }
            case RETURN -> {
                return -1;
            }
            default -> {
                return 0; // ?
            }
        }
    }
}
