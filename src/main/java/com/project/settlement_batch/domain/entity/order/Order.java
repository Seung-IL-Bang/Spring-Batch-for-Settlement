package com.project.settlement_batch.domain.entity.order;

import com.project.settlement_batch.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    private BigDecimal paidPgAmount;

    private BigDecimal mileageUsedAmount;

    private BigDecimal couponDiscountAmount;

    private LocalDateTime paidConfirmedAt;
}
