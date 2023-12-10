package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.api.AgreementRiskEntity;
import lv.javaguru.travel.insurance.core.domain.api.PolisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AgreementRiskRepository extends JpaRepository<AgreementRiskEntity, BigDecimal> {
    List<AgreementRiskEntity> findByAgreementPerson(PolisEntity polis);
}
