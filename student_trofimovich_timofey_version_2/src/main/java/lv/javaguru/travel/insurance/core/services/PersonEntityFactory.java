package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.PersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonEntityFactory {
    @Autowired
    private PersonEntityRepository repository;

     PersonEntity createPersonEntity(PersonDTO personDTO) {
         Optional<PersonEntity> optionalPersonEntity = repository.findBy(personDTO.getPersonFirstName()
                 , personDTO.getPersonLastName(), personDTO.getPersonUUID());
         if (optionalPersonEntity.isPresent()) {
             return optionalPersonEntity.get();
         } else {
             PersonEntity personEntity = new PersonEntity();
             personEntity.setFirstName(personDTO.getPersonFirstName());
             personEntity.setLastName(personDTO.getPersonLastName());
             personEntity.setPersonCode(personDTO.getPersonUUID());
             personEntity.setBirthDate(personDTO.getPersonBirthDate());
             return repository.save(personEntity);
         }
     }
}
