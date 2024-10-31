package com.project.settlement_batch.job.purchaseconfirmed.claim;

import com.project.settlement_batch.domain.entity.claim.ClaimReceiptItem;
import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import com.project.settlement_batch.domain.entity.settlement.collection.NegativeDailySettlementCollection;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class ClaimSettlementItemProcessor implements ItemProcessor<ClaimReceiptItem, SettlementDaily> {

    @Override
    public SettlementDaily process(@NonNull ClaimReceiptItem item) throws Exception {
        return new NegativeDailySettlementCollection(item).getSettlementDaily();
    }
}
