package com.project.settlement_batch.domain.entity.order;

import com.project.settlement_batch.domain.constant.DeliveryStatus;
import com.project.settlement_batch.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int id;

    private int orderId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "order_item_snapshot_id")
    private OrderItemSnapshot orderItemSnapshot;

    private int itemCount;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime purchaseConfirmedAt;

    private LocalDateTime shippedCompletedAt;

    public OrderItem purchaseConfirm() {
        this.purchaseConfirmedAt = LocalDateTime.now();
        this.deliveryStatus = DeliveryStatus.DELIVERED;
        return this;
    }
}
