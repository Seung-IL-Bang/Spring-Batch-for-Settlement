package com.project.settlement_batch.fixture.init;

import com.project.settlement_batch.domain.entity.Seller;
import com.project.settlement_batch.fixture.randomizer.BankTypeRandomizer;
import com.project.settlement_batch.fixture.randomizer.DeliveryAmountRandomizer;
import com.project.settlement_batch.fixture.randomizer.FullNameRandomizer;
import com.project.settlement_batch.fixture.randomizer.UUIDRandomizer;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;

public class SellerFixture {

    public static Seller get() {
        EasyRandom easyRandom = init();
        return easyRandom.nextObject(Seller.class);
    }

    public static EasyRandom getEasyRandom() {
        return init();
    }

    private static EasyRandom init() {
        FullNameRandomizer fullNameRandomizer = new FullNameRandomizer();
        DeliveryAmountRandomizer deliveryAmountRandomizer = new DeliveryAmountRandomizer();
        UUIDRandomizer uuidRandomizer = new UUIDRandomizer();
        BankTypeRandomizer bankTypeRandomizer = new BankTypeRandomizer();

        EasyRandomParameters params = new EasyRandomParameters().seed(System.currentTimeMillis());

        params.excludeField(FieldPredicates.named("id"));
        params.randomize(FieldPredicates.named("sellerName"), fullNameRandomizer);
        params.randomize(FieldPredicates.named("businessNo"), uuidRandomizer);
        params.randomize(FieldPredicates.named("accountNo"), new IntegerRangeRandomizer(100_000_000, 999_999_999));
        params.randomize(FieldPredicates.named("accountOwnerName"), fullNameRandomizer);
        params.randomize(FieldPredicates.named("defaultDeliveryAmount"), deliveryAmountRandomizer);
        params.randomize(FieldPredicates.named("commission"), new IntegerRangeRandomizer(1, 10));
        params.randomize(FieldPredicates.named("bankType"), bankTypeRandomizer);

        return new EasyRandom(params);
    }
}
