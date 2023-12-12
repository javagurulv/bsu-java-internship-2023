package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    @Query("SELECT agreement from Agreement agreement " +
            "WHERE agreement.dateFrom = :dateFrom " +
            "and agreement.dateTo = :dateTo " +
            "and agreement.country = :country " +
            "and agreement.premium = :premium")
    Optional<AgreementRepository> findBy(
            @Param("dateFrom") Date dateFrom,
            @Param("dateTo") Date dateTo,
            @Param("country") String country,
            @Param("premium") BigDecimal premium
    );
}
