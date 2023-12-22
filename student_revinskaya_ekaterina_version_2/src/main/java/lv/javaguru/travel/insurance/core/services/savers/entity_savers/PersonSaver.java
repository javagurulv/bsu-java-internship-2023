package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.Person;
import lv.javaguru.travel.insurance.core.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonSaver {
    @Autowired
    PersonRepository personRepository;

    public Person saveNotAlreadyExistPerson(PersonDTO personDTO) {
        return saveIfNotExist(convertToEntity(personDTO));

    }


    private Person convertToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getPersonFirstName());
        person.setLastName(personDTO.getPersonLastName());
        person.setPersonalCode(personDTO.getPersonalCode());
        person.setBirthday(personDTO.getPersonBirthDate());
        return person;
    }

    private Person saveIfNotExist(Person person) {
        Optional<Person> findingPerson =personRepository.findByPersonalCode(
                person.getPersonalCode());
        return findingPerson.orElseGet(() -> personRepository.save(person));
    }
}


