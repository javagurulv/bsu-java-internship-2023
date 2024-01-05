package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonEntity;
import lv.javaguru.travel.insurance.core.services.savers.entity_savers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicySaver {
    @Autowired
    AgreementEntitySaver agreementEntitySaver;
    @Autowired
    PersonEntitySaver personEntitySaver;
    @Autowired
    PersonAgreementEntitySaver personAgreementEntitySaver;
    @Autowired
    SelectedRiskEntitySaver selectedRiskEntitySaver;
    @Autowired
    PersonAgreementRiskEntitySaver personAgreementRiskEntitySaver;


    public void savePolicy(AgreementDTO agreementDTO) {
        AgreementEntity agreementEntity = agreementEntitySaver.saveAgreementEntity(agreementDTO);
        agreementDTO.setUuid(agreementEntity.getUuid());
        agreementDTO.getPersons()
                .forEach(personDTO -> savePersonAndPersonAgreementEntity(personDTO, agreementEntity));
        selectedRiskEntitySaver.saveRisksEntity(agreementDTO, agreementEntity);
    }

    private void savePersonAndPersonAgreementEntity(PersonDTO personDTO, AgreementEntity agreementEntity) {
        PersonEntity personEntity = personEntitySaver.saveNotAlreadyExistPersonEntity(personDTO);
        PersonAgreementEntity personAgreementEntity = personAgreementEntitySaver.savePersonAgreementEntity(personEntity, agreementEntity, personDTO);
        personAgreementRiskEntitySaver.saveAllPersonAgreementRiskEntity(personAgreementEntity, personDTO);
    }




}
