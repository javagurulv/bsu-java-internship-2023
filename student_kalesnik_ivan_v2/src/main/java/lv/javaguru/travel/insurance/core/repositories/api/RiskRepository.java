package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.api.SelectedRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RiskRepository extends JpaRepository<SelectedRiskEntity, Long> {
    List<SelectedRiskEntity> findByAgreement(AgreementEntity agreement);
}
