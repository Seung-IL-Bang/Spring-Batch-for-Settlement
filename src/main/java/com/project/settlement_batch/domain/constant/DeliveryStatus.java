package com.project.settlement_batch.domain.constant;

public enum DeliveryStatus {

    PENDING("배송 전"),
    IN_TRANSIT("배송 중"),
    DELIVERED("배송 완료");


    private String description;

    DeliveryStatus(String description) {
        this.description = description;
    }
}
