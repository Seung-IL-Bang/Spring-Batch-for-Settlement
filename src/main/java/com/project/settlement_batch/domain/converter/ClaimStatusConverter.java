package com.project.settlement_batch.domain.converter;

import com.project.settlement_batch.domain.constant.ClaimStatus;
import jakarta.persistence.AttributeConverter;

public class ClaimStatusConverter implements AttributeConverter<ClaimStatus, String> {

    @Override
    public String convertToDatabaseColumn(ClaimStatus claimStatus) {
        return claimStatus != null ? claimStatus.name() : "";
    }

    @Override
    public ClaimStatus convertToEntityAttribute(String s) {
        ClaimStatus[] values = ClaimStatus.values();
        for (ClaimStatus value : values) {
            if (value.equals(ClaimStatus.valueOf(s))) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid ClaimStatus value");
    }
}
