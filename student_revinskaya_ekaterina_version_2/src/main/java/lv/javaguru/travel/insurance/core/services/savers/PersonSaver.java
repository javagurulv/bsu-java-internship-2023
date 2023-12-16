package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.Person;
import lv.javaguru.travel.insurance.core.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class PersonSaver {
    @Autowired
    PersonRepository personRepository;

    public void saveNotAlreadyExistPersons(AgreementDTO agreement) {
        agreement.getPersons().stream()
                .map(this::convertToEntity)
                .forEach(this::saveIfNotExist);
    }

    private boolean notExist(Person person) {
        return personRepository.findBy(person.getFirstName(),
                        person.getLastName(),
                        person.getPersonalCode())
                .isEmpty();
    }

    private Person convertToEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getPersonFirstName());
        person.setLastName(personDTO.getPersonLastName());
        person.setPersonalCode(personDTO.getPersonalCode());
        person.setBirthday(personDTO.getPersonBirthDate());
        return person;
    }
    private void saveIfNotExist(Person person){
        if(notExist(person)){
            personRepository.save(person);
        }
    }

}
