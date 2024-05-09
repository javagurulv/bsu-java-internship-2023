package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import lv.javaguru.travel.insurance.core.repositories.agreement.PersonRepository;
import lv.javaguru.travel.insurance.core.services.agreement.PersonEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class PersonEntityServiceTest {
    @InjectMocks
    private PersonEntityService service;

    @Mock
    private PersonRepository personRepository;


    PersonDTO personDTO;

    AgreementEntityDomain agreementDomain = new AgreementEntityDomain();

    @Test
    public void correctCreatePersonDomainTest() {
        String firstName = "FirstName";
        String lastName = "LastName";
        Date birthDate = Date.valueOf("2005-02-03");
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");
        personDTO = PersonDTOBuilder.createPersonDTO()
                .withFirstName("FirstName")
                .withLastName("LastName")
                .withBirthDate(birthDate)
                .withIc(ic)
                .build();
        when(personRepository.findBy(firstName, lastName, ic)).thenReturn(Optional.empty());
        agreementDomain.setId(0L);
        PersonDTODomain domain = service.getPersonEntity(personDTO, agreementDomain);
        assertEquals("", firstName, domain.getPersonFirstName());
        assertEquals("", lastName, domain.getPersonLastName());
        assertEquals("", birthDate, domain.getPersonBirthDate());
        assertEquals("", ic, domain.getPersonIc());
    }
}
