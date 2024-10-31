package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class CustomPurchaseConfirmedItemQueryProvider extends AbstractJpaQueryProvider {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public CustomPurchaseConfirmedItemQueryProvider(LocalDateTime startDateTime,
                                                    LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    @NonNull
    public Query createQuery() {

        TypedQuery<OrderItem> query = this.getEntityManager()
                .createQuery(
                    "SELECT oi FROM OrderItem oi " +
                        "WHERE purchaseConfirmedAt BETWEEN :startDateTime AND :endDateTime",
                        OrderItem.class
                );

        query.setParameter("startDateTime", startDateTime);
        query.setParameter("endDateTime", endDateTime);

        return query;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
