package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entity.Agreement;
import lv.javaguru.travel.insurance.core.domain.entity.Person;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreement;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import lv.javaguru.travel.insurance.core.services.savers.entity_savers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PremiumSaver {
    @Autowired
    AgreementSaver agreementSaver;
    @Autowired
    PersonSaver personSaver;
    @Autowired
    PersonAgreementSaver personAgreementSaver;
    @Autowired
    SelectedRiskSaver selectedRiskSaver;
    @Autowired
    PersonAgreementRiskSaver personAgreementRiskSaver;


    public void savePremiums(AgreementDTO agreementDTO) {
        Agreement agreement = agreementSaver.saveAgreementEntity(agreementDTO);
        agreementDTO.setUuid(agreement.getUuid());
        agreementDTO.getPersons()
                .forEach(personDTO -> savePersonAndPersonAgreement(personDTO, agreement));
        selectedRiskSaver.saveRisks(agreementDTO, agreement);
    }

    private void savePersonAndPersonAgreement(PersonDTO personDTO, Agreement agreement) {
        Person person = personSaver.saveNotAlreadyExistPerson(personDTO);
        PersonAgreement personAgreement = personAgreementSaver.savePersonAgreement(person, agreement, personDTO);
        personAgreementRiskSaver.saveAllPersonAgreementRisk(personAgreement, personDTO);
    }




}
