package com.project.settlement_batch.job.total;

import com.project.settlement_batch.domain.projection.SummingSettlementResponse;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;

import java.time.LocalDate;

public class SummingSettlementDailyQueryProvider extends AbstractJpaQueryProvider {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public SummingSettlementDailyQueryProvider(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Query createQuery() {

        TypedQuery<SummingSettlementResponse> query = this.getEntityManager().createQuery(
                "SELECT sd.sellerId," +
                        "sd.sellerName," +
                        "sd.sellerBusinessNo," +
                        "sd.taxType," +
                        "sd.sellType," +
                        "sum(sd.pgSalesAmount) as sumPgSalesAmount," +
                        "sum(sd.couponDiscountAmount) as sumCouponDiscountAmount," +
                        "sum(sd.mileageUsedAmount) as sumMileageUsageAmount," +
                        "sum(sd.shippingFee) as sumShippingFeeAmount," +
                        "sum(sd.claimShippingFee) as sumClaimShippingFeeAmount," +
                        "sum(sd.commissionAmount) as sumCommissionAmount " +
                        "FROM SettlementDaily sd " +
                        "WHERE sd.settlementDate BETWEEN :startDate AND :endDate " +
                        "GROUP BY sellerId"
                , SummingSettlementResponse.class);

        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
