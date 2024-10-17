package com.project.settlement_batch.job.purchase_confirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class PurchaseConfirmedJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final JpaPagingItemReader deliveryCompletedJpaItemReader;
    private final ItemProcessor purchaseConfirmedProcessor;
    private final ItemWriter purchaseConfirmedWriter;

    private static final String JOB_NAME = "purchaseConfirmedJob";
    private static final int CHUNK_SIZE = 500;

    @Bean
    public Job purchaseConfirmedJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(purchaseConfirmedJobStep())
                .build();
    }

    @Bean
    @JobScope
    public Step purchaseConfirmedJobStep() {
        return new StepBuilder(JOB_NAME + "_step", jobRepository)
                .<OrderItem, OrderItem>chunk(CHUNK_SIZE, transactionManager)
                .reader(deliveryCompletedJpaItemReader)
                .processor(purchaseConfirmedProcessor)
                .writer(purchaseConfirmedWriter)
                .build();
    }

}
