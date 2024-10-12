package com.project.settlement_batch.domain.entity.order;

import com.project.settlement_batch.domain.entity.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    private BigDecimal paidPgAmount = BigDecimal.ZERO;

    private BigDecimal mileageUsedAmount = BigDecimal.ZERO;

    private BigDecimal couponDiscountAmount = BigDecimal.ZERO;

    private LocalDateTime paidConfirmedAt;
}
