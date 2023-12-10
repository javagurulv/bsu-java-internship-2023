package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.api.RiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RiskRepository extends JpaRepository<RiskEntity, Long> {
    List<RiskEntity> findByAgreement(AgreementEntity agreement);
}
