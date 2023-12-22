package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.domain.Person;
import lv.javaguru.travel.insurance.core.domain.PersonAgreement;
import lv.javaguru.travel.insurance.core.domain.PersonAgreementRisk;
import lv.javaguru.travel.insurance.core.repositories.AgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.PersonAgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.PersonAgreementRiskRepository;
import lv.javaguru.travel.insurance.core.services.savers.entity_savers.PersonAgreementRiskSaver;
import lv.javaguru.travel.insurance.core.services.savers.entity_savers.PersonSaver;
import lv.javaguru.travel.insurance.core.services.savers.entity_savers.SelectedRiskSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgreementSaver {
    @Autowired
    SelectedRiskSaver selectedRiskSaver;
    @Autowired
    PersonSaver personSaver;
    @Autowired
    AgreementRepository agreementRepository;

    @Autowired
    PersonAgreementRepository personAgreementRepository;

    @Autowired
    PersonAgreementRiskSaver personAgreementRiskSaver;

    public void saveAgreements(AgreementDTO agreementDTO) {
        Agreement agr = convertToAgreement(agreementDTO);
        Agreement agreement = saveAgreement(agr);
        agreementDTO.getPersons()
                .forEach(personDTO -> savePersonAndPersonAgreement(personDTO, agreement));
        selectedRiskSaver.saveRisks(agreementDTO, agreement);
    }

    private Agreement saveAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    private void savePersonAndPersonAgreement(PersonDTO personDTO, Agreement agreement) {
        Person person = personSaver.saveNotAlreadyExistPerson(personDTO);
        PersonAgreement personAgreement = saveNotExistPersonAgreement(convertToPersonAgreement(person, agreement, personDTO));
        personAgreementRiskSaver.saveAllPersonAgreementRisk(personAgreement, personDTO);
    }

    private PersonAgreement saveNotExistPersonAgreement(PersonAgreement personAgreement) {
        Optional<PersonAgreement> findingPersonAgreement = personAgreementRepository.findByPersonIdAndAgreementId(
                personAgreement.getPersonId(), personAgreement.getAgreementId());
        return findingPersonAgreement.orElseGet(() -> personAgreementRepository.save(personAgreement));
    }

    private Agreement convertToAgreement(AgreementDTO agreementDTO) {
        Agreement agreement = new Agreement();
        agreement.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreement.setDateTo(agreementDTO.getAgreementDateTo());
        agreement.setCountry(agreementDTO.getCountry());
        agreement.setPremium(agreementDTO.getAgreementPremium());
        return agreement;
    }

    private PersonAgreement convertToPersonAgreement(Person person, Agreement agreement, PersonDTO personDTO) {
        PersonAgreement personAgreement = new PersonAgreement();
        personAgreement.setPersonId(person);
        personAgreement.setAgreementId(agreement);
        personAgreement.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        return personAgreement;
    }


}
