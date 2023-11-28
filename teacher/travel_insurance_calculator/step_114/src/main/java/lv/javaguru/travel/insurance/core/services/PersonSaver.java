package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonSaver {

    @Autowired private PersonEntityRepository repository;

    PersonEntity savePerson(PersonDTO personDTO) {
        Optional<PersonEntity> personOpt = repository.findBy(
                personDTO.getPersonFirstName(),
                personDTO.getPersonLastName(),
                personDTO.getPersonCode());
        if (personOpt.isPresent()) {
            return personOpt.get();
        } else {
            PersonEntity person = new PersonEntity();
            person.setFirstName(personDTO.getPersonFirstName());
            person.setLastName(personDTO.getPersonLastName());
            person.setPersonCode(personDTO.getPersonCode());
            person.setBirthDate(personDTO.getPersonBirthDate());
            return repository.save(person);
        }
    }

}
