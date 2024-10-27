package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConfirmedProcessor implements ItemProcessor<OrderItem, OrderItem> {

    @Override
    public OrderItem process(OrderItem item) throws Exception {
        return item.purchaseConfirm();
    }
}
