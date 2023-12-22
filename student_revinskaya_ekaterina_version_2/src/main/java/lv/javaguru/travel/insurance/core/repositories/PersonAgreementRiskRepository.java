package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.PersonAgreement;
import lv.javaguru.travel.insurance.core.domain.PersonAgreementRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonAgreementRiskRepository extends JpaRepository<PersonAgreementRisk, Long> {
    @Query("SELECT par from PersonAgreementRisk par " +
            "where par.personAgreementId = :personAgreementId " +
            "and par.riskIc = :riskIc")
    Optional<PersonAgreementRisk> findByPersonAgreementIdAndRiskIc(@Param("personAgreementId") PersonAgreement personAgreementId,
                                                                   @Param("riskIc") String riskIc);
}
