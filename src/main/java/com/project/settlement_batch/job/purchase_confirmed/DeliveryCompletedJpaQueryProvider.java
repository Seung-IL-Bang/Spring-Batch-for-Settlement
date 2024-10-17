package com.project.settlement_batch.job.purchase_confirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;

import java.time.LocalDateTime;

public class DeliveryCompletedJpaQueryProvider extends AbstractJpaQueryProvider {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public DeliveryCompletedJpaQueryProvider(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public Query createQuery() {
        TypedQuery<OrderItem> query = this.getEntityManager()
                .createQuery("SELECT oi FROM OrderItem oi " +
                        "WHERE shippedCompletedAt BETWEEN :startDateTime AND :endDateTime", OrderItem.class)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime);
        return query;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
