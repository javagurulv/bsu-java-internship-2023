package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entity.PersonAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class PersonAgreementEntitySaver {
    @Autowired
    PersonAgreementRepository personAgreementRepository;

    public PersonAgreementEntity savePersonAgreementEntity(PersonEntity personEntity, AgreementEntity agreementEntity, PersonDTO personDTO){
        PersonAgreementEntity personAgreementEntity = convertToPersonAgreementEntity(personEntity, agreementEntity, personDTO);
        return saveNotExistPersonAgreementEntity(personAgreementEntity);
    }

    private PersonAgreementEntity convertToPersonAgreementEntity(PersonEntity personEntity, AgreementEntity agreementEntity, PersonDTO personDTO) {
        PersonAgreementEntity personAgreementEntity = new PersonAgreementEntity();
        personAgreementEntity.setPersonEntityId(personEntity);
        personAgreementEntity.setAgreementEntityId(agreementEntity);
        personAgreementEntity.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        return personAgreementEntity;
    }

    private PersonAgreementEntity saveNotExistPersonAgreementEntity(PersonAgreementEntity personAgreementEntity) {
        Optional<PersonAgreementEntity> findingPersonAgreementEntity = personAgreementRepository.findByPersonIdAndAgreementId(
                personAgreementEntity.getPersonEntityId(), personAgreementEntity.getAgreementEntityId());
        return findingPersonAgreementEntity.orElseGet(() -> personAgreementRepository.save(personAgreementEntity));
    }

}
