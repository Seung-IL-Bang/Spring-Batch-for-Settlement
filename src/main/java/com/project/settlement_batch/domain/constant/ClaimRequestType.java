package com.project.settlement_batch.domain.constant;

public enum ClaimRequestType {

    RETURN("반품"),
    REFUND("환불"),
    EXCHANGE("교환"),
    CANCELLATION("취소")
    ;

    private String description;

    ClaimRequestType(String description) {
        this.description = description;
    }
}
