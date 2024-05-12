package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementPersonEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import lv.javaguru.travel.insurance.core.repositories.agreement.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void repositoryIsNotNull() {
        assertNotNull("", personRepository);
    }
    /*
    обновить sql скрипт
    дописать тесты
     */
    @Test
    public void correctPersonTest() {
        String firstName = "CorrectFirstName";
        String lastName = "CorrectLastName";
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");
        Optional<PersonDTODomain> optional = personRepository.findBy(firstName, lastName, ic);
        assertTrue("", optional.isPresent());
        assertEquals("", firstName, optional.get().getPersonFirstName());
        assertEquals("", lastName, optional.get().getPersonLastName());
        assertEquals("", ic, optional.get().getPersonIc());
    }

    @Test
    public void wrongPersonTest() {
        String firstName = "WrongFirstName";
        String lastName = "WrongLastName";
        UUID ic = UUID.fromString("22222222-1234-1234-1234-123456789101");
        Optional<PersonDTODomain> optional = personRepository.findBy(firstName, lastName, ic);
        assertFalse("", optional.isPresent());
    }

    @Test
    public void wrongPersonFirstNameTest() {
        String firstName = "WrongFirstName";
        String lastName = "CorrectLastName";
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");
        Optional<PersonDTODomain> optional = personRepository.findBy(firstName, lastName, ic);
        assertFalse("", optional.isPresent());
    }

    @Test
    public void wrongPersonLastNameTest() {
        String firstName = "CorrectFirstName";
        String lastName = "WrongLastName";
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");
        Optional<PersonDTODomain> optional = personRepository.findBy(firstName, lastName, ic);
        assertFalse("", optional.isPresent());
    }

    @Test
    public void wrongPersonIcTest() {
        String firstName = "CorrectFirstName";
        String lastName = "CorrectLastName";
        UUID ic = UUID.fromString("22222222-1234-1234-1234-123456789101");
        Optional<PersonDTODomain> optional = personRepository.findBy(firstName, lastName, ic);
        assertFalse("", optional.isPresent());
    }

}
