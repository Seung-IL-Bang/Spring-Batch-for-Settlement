package com.project.settlement_batch.domain.constant;

public enum SellType {
    PURCHASE_SALE("매입 판매"),
    CONSIGNMENT_SALE("위탁 판매");

    private String description;

    SellType(String description) {
        this.description = description;
    }
}
