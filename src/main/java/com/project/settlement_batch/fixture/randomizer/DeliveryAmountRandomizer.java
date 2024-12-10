package com.project.settlement_batch.fixture.randomizer;

import org.jeasy.random.api.Randomizer;

public class DeliveryAmountRandomizer implements Randomizer<Integer> {
    @Override
    public Integer getRandomValue() {
        return new int[]{1000, 2000, 3000}[(int) (Math.random() * 3)];
    }
}
