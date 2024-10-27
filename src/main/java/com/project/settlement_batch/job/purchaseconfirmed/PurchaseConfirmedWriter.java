package com.project.settlement_batch.job.purchaseconfirmed;

import com.project.settlement_batch.domain.entity.order.OrderItem;
import com.project.settlement_batch.infrastructure.database.repository.OrderItemRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PurchaseConfirmedWriter implements ItemWriter<OrderItem> {

    private final OrderItemRepository orderItemRepository;

    public PurchaseConfirmedWriter(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void write(Chunk<? extends OrderItem> chunk) throws Exception {
        for (OrderItem orderItem : chunk) {
            orderItemRepository.save(orderItem);
        }
    }
}
