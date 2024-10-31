package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class DeliveryCompletedJpaQueryProvider extends AbstractJpaQueryProvider {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public DeliveryCompletedJpaQueryProvider(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    @NonNull
    public Query createQuery() {
        return this.getEntityManager()
                .createQuery(
                      "SELECT oi " +
                        "FROM OrderItem oi " +
                        "LEFT OUTER JOIN ClaimReceipt cr ON oi.id = cr.id " +
                        "WHERE oi.shippedCompletedAt BETWEEN :startDateTime AND :endDateTime " +
                        "AND oi.purchaseConfirmedAt IS NULL " +
                        "AND (cr.id IS NULL OR cr.completedAt IS NOT NULL)", OrderItem.class)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
