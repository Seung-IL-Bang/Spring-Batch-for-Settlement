package com.project.settlement_batch.fixture.randomizer;

import org.jeasy.random.api.Randomizer;

import java.util.UUID;

public class UUIDRandomizer implements Randomizer<String> {
    @Override
    public String getRandomValue() {
        return UUID.randomUUID().toString();
    }
}
