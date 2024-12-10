package com.project.settlement_batch.fixture.randomizer;

import org.jeasy.random.api.Randomizer;

public class BankTypeRandomizer implements Randomizer<String> {
    @Override
    public String getRandomValue() {
        return new String[]{"KAKAO_BANK", "TOSS_BANK", "KB_BANK", "SINHAN_BANK"}[(int) (Math.random() * 4)];
    }
}
