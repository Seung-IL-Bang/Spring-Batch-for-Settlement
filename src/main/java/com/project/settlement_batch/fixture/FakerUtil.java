package com.project.settlement_batch.fixture;

import com.github.javafaker.Faker;

public class FakerUtil {

    private static final Faker faker = new Faker();

    public static String fullName() {
        return faker.name().fullName();
    }


}
