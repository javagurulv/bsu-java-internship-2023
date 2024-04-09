package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.PersonDTODomain;
import lv.javaguru.travel.insurance.core.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class PersonEntityServiceTest {
    @InjectMocks
    private PersonEntityService service;

    @Mock
    private PersonRepository personRepository;

    PersonDTO personDTO;

    @Test
    public void correctCreatePersonDomainTest() {
        String firstName = "FirstName";
        String lastName = "LastName";
        Date birthDate = Date.valueOf("2005-02-03");
        String ic = "PERSON_IC";
        personDTO = PersonDTOBuilder.createPersonDTO()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(birthDate)
                .withIc(ic)
                .build();
        when(personRepository.findBy(firstName, lastName, ic)).thenReturn(Optional.empty());

        PersonDTODomain domain = service.getPersonEntity(personDTO);
        assertEquals("", firstName, domain.getPersonFirstName());
        assertEquals("", lastName, domain.getPersonLastName());
        assertEquals("", birthDate, domain.getPersonBirthDate());
        assertEquals("", ic, domain.getPersonIc());
    }
}
