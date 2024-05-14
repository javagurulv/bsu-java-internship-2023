package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementRiskEntityDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgreementRiskEntityRepository extends JpaRepository<AgreementRiskEntityDomain, Long> {
 //   Optional<AgreementRiskEntityDomain> findByRiskIc(String riskIc);

    @Query("SELECT risk FROM AgreementRiskEntityDomain risk " +
            "LEFT JOIN risk.agreement a " +
            "where risk.riskIc = :ic " +
            "and a = :agr")
    Optional<AgreementRiskEntityDomain> findByIcAndAgreement(@Param("ic")String riskIc,
                                                             @Param("agr")AgreementEntityDomain agreement);
}
