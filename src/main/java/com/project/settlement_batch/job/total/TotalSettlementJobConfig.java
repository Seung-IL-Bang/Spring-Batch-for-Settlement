package com.project.settlement_batch.job.total;

import com.project.settlement_batch.domain.entity.settlement.SettlementTotal;
import com.project.settlement_batch.domain.projection.SummingSettlementResponse;
import com.project.settlement_batch.infrastructure.database.repository.SettlementTotalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class TotalSettlementJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final JpaPagingItemReader<SummingSettlementResponse> totalSettlementJpaItemReader;
    private final SettlementTotalRepository settlementTotalRepository;

    private final String JOB_NAME = "totalSettlementJob";
    private static final int CHUNK_SIZE = 500;


    @Bean
    public Job totalSettlementJob() {
        return new JobBuilder(JOB_NAME, this.jobRepository)
                .start(totalSettlementJobStep())
                .build();
    }

    @Bean
    @JobScope
    public Step totalSettlementJobStep() {
        return new StepBuilder(JOB_NAME + "_step", this.jobRepository)
                .<SummingSettlementResponse, SettlementTotal>chunk(CHUNK_SIZE, this.transactionManager)
                .reader(totalSettlementJpaItemReader)
                .processor(totalSettlementItemProcessor())
                .writer(totalSettlementItemWriter())
                .build();
    }

    @Bean
    public TotalSettlementItemProcessor totalSettlementItemProcessor() {
        return new TotalSettlementItemProcessor();
    }

    @Bean
    public TotalSettlementItemWriter totalSettlementItemWriter() {
        return new TotalSettlementItemWriter(settlementTotalRepository);
    }

}
