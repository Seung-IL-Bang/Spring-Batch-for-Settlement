package com.project.settlement_batch.infrastructure.database.repository;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByShippedCompletedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
