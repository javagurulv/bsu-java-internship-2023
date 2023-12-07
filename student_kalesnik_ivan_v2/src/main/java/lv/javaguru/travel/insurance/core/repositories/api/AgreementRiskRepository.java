package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.AgreementRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface AgreementRiskRepository extends JpaRepository<AgreementRiskEntity, BigDecimal> {
    //
}
