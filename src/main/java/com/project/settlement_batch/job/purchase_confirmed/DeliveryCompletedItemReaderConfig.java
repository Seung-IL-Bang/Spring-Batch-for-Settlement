package com.project.settlement_batch.job.purchase_confirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.infrastructure.database.repository.OrderItemRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Map;

@Configuration
public class DeliveryCompletedItemReaderConfig {

    private static final int CHUNK_SIZE = 500;
    private LocalDateTime startDateTime = LocalDateTime.now();
    private LocalDateTime endDateTime = LocalDateTime.now().plusDays(1);


    @Bean
    public RepositoryItemReader<OrderItem> deliveryCompletedJpaItemReader(OrderItemRepository orderItemRepository) {
        return new RepositoryItemReaderBuilder<OrderItem>()
                .name("deliveryCompletedJpaItemReader")
                .repository(orderItemRepository)
                .methodName("findByShippedCompletedAtBetween")
                .arguments(startDateTime, endDateTime)
                .pageSize(CHUNK_SIZE) // TODO 주입 받는 parameter로 분리
                .sorts(Map.of("shippedCompletedAt", Sort.Direction.ASC))
                .build();
    }


}
