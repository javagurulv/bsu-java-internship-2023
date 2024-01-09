package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.SelectedRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SelectedRiskRepository extends JpaRepository<SelectedRisk, Long> {
    @Query("SELECT risk from SelectedRisk risk " +
            "where risk.agreementEntityId = :agreementEntityId " +
    "and risk.riskIc = :riskIc")
    Optional<SelectedRisk> findByAgreementIdAndRiskIc(
            @Param("agreementEntityId") AgreementEntity agreementEntityId,
            @Param("riskIc") String riskIc
    );

    List<SelectedRisk> findByAgreementEntityId(
            @Param("agreementEntityId") AgreementEntity agreementEntityId
    );
}
