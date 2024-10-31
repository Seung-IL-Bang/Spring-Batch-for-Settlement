package com.project.settlement_batch.job.total;

import com.project.settlement_batch.domain.projection.SummingSettlementResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class TotalSettlementItemReaderConfig {

    @PersistenceContext
    private EntityManager em;

    private final int CHUNK_SIZE = 500;
    private final LocalDate currentDate = LocalDate.now();

    @Bean
    public JpaPagingItemReader<SummingSettlementResponse> totalSettlementJpaItemReader() {

        SummingSettlementDailyQueryProvider queryProvider = new SummingSettlementDailyQueryProvider(getFirstDate(), getLastDate());

        return new JpaPagingItemReaderBuilder<SummingSettlementResponse>()
                .name("totalSettlementJpaItemReader")
                .entityManagerFactory(em.getEntityManagerFactory())
                .pageSize(CHUNK_SIZE)
                .queryProvider(queryProvider)
                .build();
    }


    private LocalDate getFirstDate() {

        int year = this.currentDate.getYear();
        int month = this.currentDate.getMonthValue();

        if (month == 1) {
            return LocalDate.of(year - 1, 12, 1);
        } else {
            return LocalDate.of(year, month - 1 , 1);
        }
    }

    private LocalDate getLastDate() {
        return this.currentDate.withDayOfMonth(1);
    }
}
