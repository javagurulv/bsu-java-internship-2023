package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.AgreementPersonEntityRepository;
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
    @Autowired
    private AgreementPersonEntityRepository agreementPersonEntityRepository;


    AgreementEntity createAgreementEntity(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = saveAgreement(agreementDTO);
        saveSelectedRisks(agreementDTO, agreementEntity);
        savePersonAgreements(agreementDTO, agreementEntity);
        return agreementEntity;
    }

    private void savePersonAgreements(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(
                personDTO -> {
            AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
            agreementPersonEntity.setAgreement(agreementEntity);
            PersonEntity personEntity = personEntityFactory.createPersonEntity(personDTO);
            agreementPersonEntity.setPerson(personEntity);
            agreementPersonEntity.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
            agreementPersonEntityRepository.save(agreementPersonEntity);
        });
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
}
