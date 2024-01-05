package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonAgreementRiskRepository extends JpaRepository<PersonAgreementRiskEntity, Long> {
    @Query("SELECT par from PersonAgreementRiskEntity par " +
            "where par.personAgreementEntityId = :personAgreementEntityId " +
            "and par.riskIc = :riskIc")
    Optional<PersonAgreementRiskEntity> findByPersonAgreementIdAndRiskIc(@Param("personAgreementEntityId") PersonAgreementEntity personAgreementEntityId,
                                                                         @Param("riskIc") String riskIc);
    @Query("SELECT par from PersonAgreementRiskEntity par " +
            "where par.personAgreementEntityId = :personAgreementEntityId ")
    List<PersonAgreementRiskEntity> findByPersonAgreementId(@Param("personAgreementEntityId") PersonAgreementEntity personAgreementEntityId);
}
