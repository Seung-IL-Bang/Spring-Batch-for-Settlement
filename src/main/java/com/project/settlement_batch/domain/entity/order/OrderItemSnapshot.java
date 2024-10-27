package com.project.settlement_batch.domain.entity.order;

import com.project.settlement_batch.domain.constant.Category;
import com.project.settlement_batch.domain.constant.TaxType;
import com.project.settlement_batch.domain.entity.BaseEntity;
import com.project.settlement_batch.domain.entity.Product;
import com.project.settlement_batch.domain.entity.Seller;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class OrderItemSnapshot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_snapshot_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private BigDecimal sellPrice = BigDecimal.ZERO;

    private BigDecimal supplyPrice = BigDecimal.ZERO;

    private BigDecimal promotionAmount = BigDecimal.ZERO;

    private BigDecimal mileageUsedAmount = BigDecimal.ZERO;

    private BigDecimal defaultDeliveryAmount;

    private int taxRate;

    @Enumerated(EnumType.STRING)
    private TaxType taxType;

    @Enumerated(EnumType.STRING)
    private Category category;

}
