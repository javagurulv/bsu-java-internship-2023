package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
class AgreementEntityFactory {
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;
    @Autowired
    private SelectedRiskEntityRepository selectedRiskEntityRepository;
    @Autowired
    private PersonEntityFactory personEntityFactory;

    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO) {
        saveAllPersons(agreementDTO);
        AgreementEntity agreementEntity = saveAgreement(agreementDTO);
        saveSelectedRisks(agreementDTO, agreementEntity);
        return agreementEntity;
    }

    private void saveSelectedRisks(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getSelectedRisks().forEach(
                riskIc -> {
                    SelectedRiskEntity selectedRiskEntity = new SelectedRiskEntity();
                    selectedRiskEntity.setAgreement(agreementEntity);
                    selectedRiskEntity.setRiskIc(riskIc);
                    selectedRiskEntityRepository.save(selectedRiskEntity);
                }
        );
    }

    private AgreementEntity saveAgreement(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setAgreementPremium(agreementDTO.getAgreementPremium());
        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllPersons(AgreementDTO agreementDTO) {
        agreementDTO.getPersons().forEach(personEntityFactory::createPersonEntity);
    }
}
