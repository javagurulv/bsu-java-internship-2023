package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.Agreement;
import lv.javaguru.travel.insurance.core.domain.entity.SelectedRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SelectedRiskRepository extends JpaRepository<SelectedRisk, Long> {
    @Query("SELECT risk from SelectedRisk risk " +
            "where risk.agreementId = :agreementId " +
    "and risk.riskIc = :riskIc")
    Optional<SelectedRisk> findByAgreementIdAndRiskIc(
            @Param("agreementId") Agreement agreementId,
            @Param("riskIc") String riskIc
    );
    @Query("SELECT risks from SelectedRisk risks " +
            "where risks.agreementId = :agreementId ")
    List<SelectedRisk> findByAgreementId(
            @Param("agreementId") Agreement agreementId
    );
}
