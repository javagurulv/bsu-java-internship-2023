package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entity.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entity.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonEntitySaver {
    @Autowired
    PersonRepository personRepository;

    public PersonEntity saveNotAlreadyExistPersonEntity(PersonDTO personDTO) {
        return saveIfNotExistPersonEntity(convertToPersonEntity(personDTO));

    }

    private PersonEntity convertToPersonEntity(PersonDTO personDTO) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(personDTO.getPersonFirstName());
        personEntity.setLastName(personDTO.getPersonLastName());
        personEntity.setPersonalCode(personDTO.getPersonalCode());
        personEntity.setBirthday(personDTO.getPersonBirthDate());
        return personEntity;
    }

    private PersonEntity saveIfNotExistPersonEntity(PersonEntity personEntity) {
        Optional<PersonEntity> findingPersonEntity =personRepository.findByPersonalCode(
                personEntity.getPersonalCode());
        return findingPersonEntity.orElseGet(() -> personRepository.save(personEntity));
    }
}


