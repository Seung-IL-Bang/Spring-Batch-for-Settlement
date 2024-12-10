package com.project.settlement_batch.domain.entity;

import com.project.settlement_batch.domain.constant.Category;
import com.project.settlement_batch.domain.constant.TaxType;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    private int sellerId;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private TaxType taxType;

    private BigDecimal sellPrice = BigDecimal.ZERO;

    private BigDecimal supplyPrice = BigDecimal.ZERO;

    private Boolean isActive;

}
