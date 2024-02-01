package lv.javaguru.travel.insurance.core.services.entities;

import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRiskEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementPersonRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementPersonRiskEntityFactory {
    @Autowired
    private AgreementPersonRiskEntityRepository repository;

    void createAgreementPersonRiskEntity(AgreementPersonEntity agreementPersonEntity,
                                         RiskDTO riskDTO) {
        AgreementPersonRiskEntity agreementPersonRiskEntity = new AgreementPersonRiskEntity();
        agreementPersonRiskEntity.setAgreementPersonEntity(agreementPersonEntity);
        agreementPersonRiskEntity.setRiskIc(riskDTO.getRiskIc());
        agreementPersonRiskEntity.setPremium(riskDTO.getPremium());
        repository.save(agreementPersonRiskEntity);
    }
}
