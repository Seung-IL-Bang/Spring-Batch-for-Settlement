package com.project.settlement_batch.domain.entity;

import com.project.settlement_batch.domain.constant.SellType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int id;

    @Column(length = 50)
    private String sellerName;

    private String businessNo;

    @Enumerated(EnumType.STRING)
    private SellType sellType;

    private String bankType;

    private int accountNo;

    private String accountOwnerName;

    private Boolean isActive;

    private int defaultDeliveryAmount;

    private int commission;

}
