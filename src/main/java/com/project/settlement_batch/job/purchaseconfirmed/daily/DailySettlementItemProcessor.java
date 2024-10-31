package com.project.settlement_batch.job.purchaseconfirmed.daily;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.domain.entity.settlement.SettlementDaily;
import com.project.settlement_batch.domain.entity.settlement.collection.PositiveDailySettlementCollection;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class DailySettlementItemProcessor implements ItemProcessor<OrderItem, SettlementDaily> {


    /**
     * 정산 금액 만들기
     */
    @Override
    public SettlementDaily process(@NonNull OrderItem orderItem) throws Exception {

        PositiveDailySettlementCollection positiveDailySettlementCollection = new PositiveDailySettlementCollection(orderItem);

        return positiveDailySettlementCollection.getSettlementDaily();
    }

}
