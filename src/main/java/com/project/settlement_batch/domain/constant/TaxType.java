package com.project.settlement_batch.domain.constant;

public enum TaxType {
    EXEMPT("영세"),
    ZERO_RATE("면세"),
    TAXABLE("과세")
    ;

    private String description;

    TaxType(String description) {
        this.description = description;
    }
}
