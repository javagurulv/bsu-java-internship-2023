package lv.javaguru.travel.insurance.core.services.agreement;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import lv.javaguru.travel.insurance.core.repositories.agreement.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class PersonEntityService {
    @Autowired
    private PersonRepository personRepository;


    public PersonDTODomain getPersonEntity(PersonDTO person, AgreementEntityDomain agreement) {
        Optional<PersonDTODomain> entity = personRepository.findBy(
                person.getPersonFirstName(),
                person.getPersonLastName(),
                person.getPersonIc()
        );
        if (entity.isPresent()) {
            return entity.get();
        }
        PersonDTODomain result = new PersonDTODomain();
        result.setPersonFirstName(person.getPersonFirstName());
        result.setPersonLastName(person.getPersonLastName());


        result.setPersonIc(person.getPersonIc());
        result.setPersonBirthDate(new Date(person.getPersonBirthDate().getTime()));
        personRepository.save(result);
        return result;
    }
}
