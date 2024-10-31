package com.project.settlement_batch.job.purchaseconfirmed.daily;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.job.purchaseconfirmed.CustomPurchaseConfirmedItemQueryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
public class DailySettlementItemReaderConfig {

    private static final int CHUNK_SIZE = 500;
    private final LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    private final LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JpaPagingItemReader<OrderItem> dailySettlementJpaItemReader() {
        CustomPurchaseConfirmedItemQueryProvider customQueryProvider
                = new CustomPurchaseConfirmedItemQueryProvider(startDateTime, endDateTime);

        return new JpaPagingItemReaderBuilder<OrderItem>()
                .name("dailySettlementJpaItemReader")
                .entityManagerFactory(em.getEntityManagerFactory())
                .pageSize(CHUNK_SIZE)
                .queryProvider(customQueryProvider)
                .build();
    }

}
