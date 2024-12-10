package com.project.settlement_batch.fixture.randomizer;

import org.jeasy.random.api.Randomizer;

import java.time.LocalDateTime;

public class DateTimeRandomizer implements Randomizer<LocalDateTime> {

    @Override
    public LocalDateTime getRandomValue() {
        LocalDateTime now = LocalDateTime.now();
        long randomDays = (int) (Math.random() * 30) + 1;
        return now.minusDays(randomDays);
    }
}
