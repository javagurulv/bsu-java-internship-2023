package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entity.Agreement;
import lv.javaguru.travel.insurance.core.domain.entity.Person;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreement;
import lv.javaguru.travel.insurance.core.repositories.entity.PersonAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class PersonAgreementSaver {
    @Autowired
    PersonAgreementRepository personAgreementRepository;

    public PersonAgreement savePersonAgreement(Person person, Agreement agreement, PersonDTO personDTO){
        PersonAgreement personAgreement = convertToPersonAgreement(person, agreement, personDTO);
        return saveNotExistPersonAgreement(personAgreement);
    }

    private PersonAgreement convertToPersonAgreement(Person person, Agreement agreement, PersonDTO personDTO) {
        PersonAgreement personAgreement = new PersonAgreement();
        personAgreement.setPersonId(person);
        personAgreement.setAgreementId(agreement);
        personAgreement.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        return personAgreement;
    }

    private PersonAgreement saveNotExistPersonAgreement(PersonAgreement personAgreement) {
        Optional<PersonAgreement> findingPersonAgreement = personAgreementRepository.findByPersonIdAndAgreementId(
                personAgreement.getPersonId(), personAgreement.getAgreementId());
        return findingPersonAgreement.orElseGet(() -> personAgreementRepository.save(personAgreement));
    }

}
