package lv.javaguru.travel.insurance.core.services.factory;

import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.domain.api.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.api.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public
class EntityManagerFactorySaver {

    @Autowired private PersonRepository repository;

    PersonEntity savePerson(PersonDto personDto) {
        Optional<PersonEntity> existingPerson = repository.findBy(
                personDto.getPersonFirstName(),
                personDto.getPersonLastName(),
                personDto.getPersonCode());

        if (existingPerson.isPresent()) {
            return existingPerson.get();
        } else {
            PersonEntity person = new PersonEntity();
            person.setFirstName(personDto.getPersonFirstName());
            person.setLastName(personDto.getPersonLastName());
            person.setPersonCode(personDto.getPersonCode());
            person.setBirthDate(personDto.getPersonBirthDate());
            return repository.save(person);
        }
    }

}
