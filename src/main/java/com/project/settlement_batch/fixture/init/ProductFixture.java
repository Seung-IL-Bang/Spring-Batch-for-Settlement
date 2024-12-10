package com.project.settlement_batch.fixture.init;

import com.project.settlement_batch.domain.entity.Product;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

public class ProductFixture {

    public static Product get() {
        EasyRandom easyRandom = init();
        return easyRandom.nextObject(Product.class);
    }

    public static EasyRandom getEasyRandom() {
        return init();
    }

    private static EasyRandom init() {

        EasyRandomParameters params = new EasyRandomParameters().seed(System.currentTimeMillis());

        params.excludeField(FieldPredicates.named("id"));

        return new EasyRandom(params);
    }
}
