package com.project.settlement_batch.fixture.randomizer;

import org.jeasy.random.api.Randomizer;

import java.math.BigDecimal;
import java.util.Random;

public class DecimalAmountRandomizer implements Randomizer<BigDecimal> {

    private int start;
    private int end;

    public DecimalAmountRandomizer(int start, int end) {
        if (start == 0 && end == 0) {
            throw new IllegalStateException("start and end must be set");
        }

        if (start > end) {
            throw new IllegalStateException("start must be less than end");
        }

        if (start < 0 || end < 0) {
            throw new IllegalStateException("start and end must be positive");
        }

        this.start = start;
        this.end = end;
    }


    @Override
    public BigDecimal getRandomValue() {
        // 두 값 사이의 범위를 10원 단위로 조정
        int range = (end - start) / 10 + 1;

        // 랜덤 값 생성
        Random random = new Random();
        int randomValue = random.nextInt(range) * 10 + start;

        // 10원 단위로 내림 (추가 안전성)
        return BigDecimal.valueOf(randomValue - (randomValue % 10));
    }
}
