package com.project.settlement_batch.fixture.randomizer;

import com.project.settlement_batch.fixture.FakerUtil;
import org.jeasy.random.api.Randomizer;

public class FullNameRandomizer implements Randomizer<String> {
    @Override
    public String getRandomValue() {
        return FakerUtil.fullName();
    }
}
