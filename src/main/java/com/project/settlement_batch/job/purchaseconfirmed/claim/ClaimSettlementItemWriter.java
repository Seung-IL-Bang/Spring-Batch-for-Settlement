package com.project.settlement_batch.job.purchaseconfirmed.claim;

import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import com.project.settlement_batch.infrastructure.database.repository.SettlementDailyRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClaimSettlementItemWriter implements ItemWriter<SettlementDaily> {

    private final SettlementDailyRepository settlementDailyRepository;

    public ClaimSettlementItemWriter(SettlementDailyRepository settlementDailyRepository) {
        this.settlementDailyRepository = settlementDailyRepository;
    }

    @Override
    public void write(Chunk<? extends SettlementDaily> chunk) throws Exception {
        for (SettlementDaily settlementDaily : chunk) {
            settlementDailyRepository.save(settlementDaily);
        }
    }
}
