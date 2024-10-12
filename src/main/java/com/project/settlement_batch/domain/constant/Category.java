package com.project.settlement_batch.domain.constant;

public enum Category {

    ITEM_ONE("상품1"),
    ITEM_TWO("상품2"),

    ITEM_THREE("상품3");

    private String description;

    Category(String description) {
        this.description = description;
    }
}
