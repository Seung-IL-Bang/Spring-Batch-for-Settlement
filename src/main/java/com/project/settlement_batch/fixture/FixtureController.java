package com.project.settlement_batch.fixture;

import com.project.settlement_batch.domain.entity.Product;
import com.project.settlement_batch.domain.entity.Seller;
import com.project.settlement_batch.domain.entity.order.Order;
import com.project.settlement_batch.domain.entity.order.OrderItemSnapshot;
import com.project.settlement_batch.fixture.init.OrderFixture;
import com.project.settlement_batch.fixture.init.OrderItemSnapshotFixture;
import com.project.settlement_batch.fixture.init.ProductFixture;
import com.project.settlement_batch.fixture.init.SellerFixture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FixtureController {

    /** 주의사항 !
     * EasyRandom 으로 랜덤 엔티티를 생성하려면, Getter 필수.
     */

    @PostMapping("/fixture/seller")
    public Seller createSellerFixture() {
        return SellerFixture.get();
    }

    @PostMapping("/fixture/order")
    public Order createOrderFixture() {
        return OrderFixture.get();
    }

    @PostMapping("/fixture/product")
    public Product createProductFixture() {
        return ProductFixture.get();
    }

    @PostMapping("/fixture/order-item-snapshot")
    public OrderItemSnapshot createOrderItemSnapshotFixture() {
        return OrderItemSnapshotFixture.get();
    }

}
