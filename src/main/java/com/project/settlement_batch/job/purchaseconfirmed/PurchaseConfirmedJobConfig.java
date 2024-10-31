package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;
import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import com.project.settlement_batch.infrastructure.database.repository.SettlementDailyRepository;
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

    private final JpaPagingItemReader dailySettlementJpaItemReader;
    private final JpaPagingItemReader claimSettlementJpaItemReader;
    private final SettlementDailyRepository settlementDailyRepository;

    private static final String JOB_NAME = "purchaseConfirmedJob";
    private static final int CHUNK_SIZE = 500;

    @Bean
    public Job purchaseConfirmedJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(purchaseConfirmedJobStep())
                .next(dailySettlementJobStep())
                .next(claimSettlementJobStep())
                .build();
    }

    @Bean
    @JobScope
    public Step purchaseConfirmedJobStep() { // 미클레임건이면서 일정 시간지난 배송 완료건 -> 구매 확정
        return new StepBuilder(JOB_NAME + "_step", jobRepository)
                .<OrderItem, OrderItem>chunk(CHUNK_SIZE, transactionManager)
                .reader(deliveryCompletedJpaItemReader)
                .processor(purchaseConfirmedProcessor)
                .writer(purchaseConfirmedWriter)
                .build();
    }

    @Bean
    @JobScope
    public Step dailySettlementJobStep() { // 구매 확정건 조회 ->
        return new StepBuilder(JOB_NAME + "_dailySettlement_step", jobRepository)
                .<OrderItem, SettlementDaily>chunk(CHUNK_SIZE, transactionManager)
                .reader(dailySettlementJpaItemReader)
                .processor(dailySettlementItemProcessor())
                .writer(dailySettlementItemWriter())
                .build();
    }

    @Bean
    public DailySettlementItemProcessor dailySettlementItemProcessor() {
        return new DailySettlementItemProcessor();
    }

    @Bean
    public DailySettlementItemWriter dailySettlementItemWriter() {
        return new DailySettlementItemWriter(settlementDailyRepository);
    }

    @Bean
    @JobScope
    public Step claimSettlementJobStep() {
        return new StepBuilder(JOB_NAME + "_claimSettlement_step", jobRepository)
                .<ClaimReceiptItem, SettlementDaily>chunk(CHUNK_SIZE, transactionManager)
                .reader(claimSettlementJpaItemReader)
                .processor(claimSettlementItemProcessor())
                .writer(claimSettlementItemWriter())
                .build();
    }

    @Bean
    public ClaimSettlementItemProcessor claimSettlementItemProcessor() {
        return new ClaimSettlementItemProcessor();
    }

    @Bean
    public ClaimSettlementItemWriter claimSettlementItemWriter() {
        return new ClaimSettlementItemWriter(settlementDailyRepository);
    }
}
