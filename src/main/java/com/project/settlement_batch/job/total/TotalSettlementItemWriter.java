package com.project.settlement_batch.job.total;

import com.project.settlement_batch.domain.entity.settlement.SettlementTotal;
import com.project.settlement_batch.infrastructure.database.repository.SettlementTotalRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class TotalSettlementItemWriter implements ItemWriter<SettlementTotal> {

    private final SettlementTotalRepository settlementTotalRepository;

    public TotalSettlementItemWriter(SettlementTotalRepository settlementTotalRepository) {
        this.settlementTotalRepository = settlementTotalRepository;
    }

    @Override
    public void write(Chunk<? extends SettlementTotal> chunk) throws Exception {
        for (SettlementTotal item : chunk) {
            settlementTotalRepository.save(item);
        }
    }
}
