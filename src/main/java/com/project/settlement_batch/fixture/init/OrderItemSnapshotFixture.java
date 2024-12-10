package com.project.settlement_batch.fixture.init;

import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

public class OrderItemSnapshotFixture {

    public static OrderItemSnapshot get() {
        EasyRandom easyRandom = init();
        return easyRandom.nextObject(OrderItemSnapshot.class);
    }

    public static EasyRandom getEasyRandom() {
        return init();
    }

    private static EasyRandom init() {

        EasyRandomParameters params = new EasyRandomParameters().seed(System.currentTimeMillis());

        params.excludeField(FieldPredicates.named("id"));
        params.randomize(FieldPredicates.named("product"), ProductFixture::get);
        params.randomize(FieldPredicates.named("seller"), SellerFixture::get);

        return new EasyRandom(params);
    }
}
