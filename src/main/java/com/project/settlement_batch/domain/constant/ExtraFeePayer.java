package com.project.settlement_batch.domain.constant;

public enum ExtraFeePayer {
    SELLER("판매자"),
    PLATFORM("플랫폼"),
    CUSTOMER("고객");

    private String description;

    ExtraFeePayer(String description) {
        this.description = description;
    }
}
