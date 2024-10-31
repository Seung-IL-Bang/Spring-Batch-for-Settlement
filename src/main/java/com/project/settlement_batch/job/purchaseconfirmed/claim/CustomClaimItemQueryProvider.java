package com.project.settlement_batch.job.purchaseconfirmed.claim;

import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

public class CustomClaimItemQueryProvider extends AbstractJpaQueryProvider {

    @Override
    @NonNull
    public Query createQuery() {
        return this.getEntityManager()
                .createQuery("SELECT ci FROM ClaimReceiptItem ci " +
                        "LEFT OUTER JOIN SettlementDaily sd ON sd.claimReceiptId = ci.id " +
                        "JOIN ClaimReceipt cr ON ci.claimReceipt.claimReceiptId = cr.claimReceiptId " +
                        "WHERE cr.completedAt IS NOT NULL AND sd.id IS NULL", ClaimReceiptItem.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
