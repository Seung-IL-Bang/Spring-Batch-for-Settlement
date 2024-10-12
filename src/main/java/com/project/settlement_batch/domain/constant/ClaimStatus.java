package com.project.settlement_batch.domain.constant;

public enum ClaimStatus {

    RECEIVED("접수"),
    IN_PROGRESS("처리 중"),
    COMPLETED("처리 완료");

    private String description;

    ClaimStatus(String description) {
        this.description = description;
    }
}
