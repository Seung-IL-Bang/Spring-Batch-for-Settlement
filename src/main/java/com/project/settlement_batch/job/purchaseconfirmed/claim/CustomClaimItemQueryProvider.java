package com.project.settlement_batch.job.purchaseconfirmed.claim;

import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;

public class CustomClaimItemQueryProvider extends AbstractJpaQueryProvider {

    @Override
    public Query createQuery() {

        TypedQuery<ClaimReceiptItem> query = this.getEntityManager()
                .createQuery("SELECT ci FROM ClaimReceiptItem ci " +
                        "LEFT OUTER JOIN SettlementDaily sd ON sd.claimReceiptItemId = ci.claimReceiptItemId " +
                        "JOIN ClaimReceipt cr ON ci.claimReceipt.claimReceiptId = cr.claimReceiptId " +
                        "WHERE cr.completedAt IS NOT NULL AND sd.id IS NULL", ClaimReceiptItem.class);

        return query;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
