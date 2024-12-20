package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DeliveryCompletedItemReaderConfig {

    private static final int CHUNK_SIZE = 500;
    private final LocalDateTime startDateTime = LocalDateTime.now();
    private final LocalDateTime endDateTime = LocalDateTime.now().plusDays(1);


    @Bean
    public JpaPagingItemReader<OrderItem> deliveryCompletedJpaItemReader(EntityManagerFactory entityManagerFactory) {

        DeliveryCompletedJpaQueryProvider queryProvider = new DeliveryCompletedJpaQueryProvider(startDateTime, endDateTime);

        return new JpaPagingItemReaderBuilder<OrderItem>()
                .name("deliveryCompletedJpaItemReader")
                .pageSize(CHUNK_SIZE) // TODO 주입 받는 parameter로 분리
                .queryProvider(queryProvider)
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}
