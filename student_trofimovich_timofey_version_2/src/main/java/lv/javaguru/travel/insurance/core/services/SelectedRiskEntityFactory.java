package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class SelectedRiskEntityFactory {
    @Autowired
    private SelectedRiskEntityRepository repository;
    SelectedRiskEntity createSelectedRiskEntity(String riskIc, AgreementEntity agreementEntity) {
        SelectedRiskEntity selectedRiskEntity = new SelectedRiskEntity();
        selectedRiskEntity.setAgreement(agreementEntity);
        selectedRiskEntity.setRiskIc(riskIc);
        return repository.save(selectedRiskEntity);
    }
}
