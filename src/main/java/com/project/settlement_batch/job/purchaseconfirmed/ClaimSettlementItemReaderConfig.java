package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClaimSettlementItemReaderConfig {

    private final int CHUNK_SIZE = 500;

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JpaPagingItemReader<ClaimReceiptItem> claimSettlementJpaItemReader() {

        CustomClaimItemQueryProvider queryProvider = new CustomClaimItemQueryProvider();

        return new JpaPagingItemReaderBuilder<ClaimReceiptItem>()
                .name("claimSettlementJpaItemReader")
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .pageSize(CHUNK_SIZE)
                .queryProvider(queryProvider)
                .build();

    }
}
