package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.domain.Person;
import lv.javaguru.travel.insurance.core.domain.SelectedRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SelectedRiskRepository extends JpaRepository<SelectedRisk, Long> {
    @Query("SELECT risk from SelectedRisk risk " +
            "where risk.agreementId = :agreementId " +
    "and risk.riskIc = :riskIc")
    Optional<SelectedRisk> findByAgreementIdAndRiskIc(
            @Param("agreementId") Agreement agreementId,
            @Param("riskIc") String riskIc
    );
}
