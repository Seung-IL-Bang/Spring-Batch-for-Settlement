package com.project.settlement_batch;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.project.settlement_batch.domain.entity.order.OrderItem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class FixtureMonkeyTest {


    @Test
    void test() {
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();

        // when
        OrderItem actual = fixtureMonkey.giveMeOne(OrderItem.class);

        System.out.println("actual = " + actual);

        // then
        then(actual).isNotNull();
    }
}
