package com.project.settlement_batch.fixture.init;

import com.project.settlement_batch.domain.entity.order.Order;
import com.project.settlement_batch.fixture.randomizer.DateTimeRandomizer;
import com.project.settlement_batch.fixture.randomizer.DecimalAmountRandomizer;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import java.time.LocalDateTime;

public class OrderFixture {

    public static Order get() {
        EasyRandom easyRandom = init();
        return easyRandom.nextObject(Order.class);
    }

    public static EasyRandom getEasyRandom() {
        return init();
    }

    private static EasyRandom init() {
        DecimalAmountRandomizer paidPgAmountRandomizer = new DecimalAmountRandomizer(5000, 100_000);
        DecimalAmountRandomizer mileageUsedAmountRandomizer = new DecimalAmountRandomizer(1000, 3000);
        DecimalAmountRandomizer couponDiscountAmountRandomizer = new DecimalAmountRandomizer(500, 2000);
        DateTimeRandomizer dateTimeRandomizer = new DateTimeRandomizer();

        EasyRandomParameters params = new EasyRandomParameters().seed(System.currentTimeMillis());

        params.excludeField(FieldPredicates.named("id"));
        params.randomize(FieldPredicates.named("paidPgAmount"), paidPgAmountRandomizer);
        params.randomize(FieldPredicates.named("mileageUsedAmount"), mileageUsedAmountRandomizer);
        params.randomize(FieldPredicates.named("couponDiscountAmount"), couponDiscountAmountRandomizer);
        params.randomize(FieldPredicates.named("paidConfirmedAt"), dateTimeRandomizer);

        return new EasyRandom(params);
    }
}
