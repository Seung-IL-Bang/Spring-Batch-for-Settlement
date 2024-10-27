package com.project.settlement_batch.domain.constant;

public enum TaxType {
    FREE("면세"),
    ZERO("영세"),
    TAXABLE("과세")
    ;

    private String description;

    TaxType(String description) {
        this.description = description;
    }
}
