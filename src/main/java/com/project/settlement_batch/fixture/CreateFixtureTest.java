package com.project.settlement_batch.fixture;

import com.github.javafaker.Faker;
import com.project.settlement_batch.domain.entity.claim.ClaimReceipt;
import org.jeasy.random.EasyRandom;

public class CreateFixtureTest {

    public static void main(String[] args) {

        EasyRandom easyRandom = new EasyRandom();

        Faker faker = new Faker();


        ClaimReceipt claimReceipt = easyRandom.nextObject(ClaimReceipt.class);

        System.out.println("claimReceipt = " + claimReceipt);



    }

}
