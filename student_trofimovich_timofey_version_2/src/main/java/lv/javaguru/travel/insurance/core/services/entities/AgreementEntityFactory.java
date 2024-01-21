package lv.javaguru.travel.insurance.core.services.entities;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.*;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AgreementEntityFactory {
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;
    @Autowired
    private SelectedRiskEntityFactory selectedRiskEntityFactory;
    @Autowired
    private AgreementPersonEntityFactory agreementPersonEntityFactory;
    @Autowired
    private AgreementPersonRiskEntityFactory agreementPersonRiskEntityFactory;


    public AgreementEntity createAgreementEntity(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = saveAgreement(agreementDTO);
        saveSelectedRisks(agreementDTO, agreementEntity);
        savePersonAgreements(agreementDTO, agreementEntity);
        return agreementEntity;
    }

    private void savePersonAgreements(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(
                personDTO -> {
                    AgreementPersonEntity agreementPersonEntity =
                            agreementPersonEntityFactory.createAgreementPersonEntity(personDTO,
                            agreementEntity);
                    saveAgreementPersonRisks(personDTO, agreementPersonEntity);
                });
    }

    private void saveAgreementPersonRisks(PersonDTO personDTO,
                                          AgreementPersonEntity agreementPersonEntity) {
        personDTO.getSelectedRisks().forEach(
                riskDTO -> agreementPersonRiskEntityFactory.createAgreementPersonRiskEntity(
                        agreementPersonEntity, riskDTO)
                );

    }

    private void saveSelectedRisks(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getSelectedRisks().forEach(
                riskIc -> selectedRiskEntityFactory.createSelectedRiskEntity(riskIc, agreementEntity)
        );
    }

    private AgreementEntity saveAgreement(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = new AgreementEntity();
        agreementEntity.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        agreementEntity.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        agreementEntity.setCountry(agreementDTO.getCountry());
        agreementEntity.setAgreementPremium(agreementDTO.getAgreementPremium());
        agreementEntity.setUuid(agreementDTO.getUuid());
        return agreementEntityRepository.save(agreementEntity);
    }
}