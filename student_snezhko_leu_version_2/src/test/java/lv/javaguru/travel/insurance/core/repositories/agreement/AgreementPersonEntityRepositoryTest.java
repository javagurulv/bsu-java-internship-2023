package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementPersonEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AgreementPersonEntityRepositoryTest {
    @Autowired
    private AgreementPersonEntityRepository agreementPersonEntityRepository;

    private PersonDTO person;
    private AgreementEntityDomain agreement;

    @Test
    public void isNotNull() {
        assertNotNull("", agreementPersonEntityRepository);
    }

    @Test
    public void correctPerson() {
        String firstName = "CorrectFirstName";
        String lastName = "CorrectLastName";
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");
        Optional<AgreementPersonEntityDomain> op = agreementPersonEntityRepository.findByName(firstName, lastName, ic);

        assertTrue("", op.isPresent());
        assertEquals("", firstName, op.get().getPerson().getPersonFirstName());
        assertEquals("", lastName, op.get().getPerson().getPersonLastName());
        assertEquals("", ic, op.get().getPerson().getPersonIc());
        assertEquals("", Date.valueOf("2001-01-01"), op.get().getPerson().getPersonBirthDate());
        assertEquals("", "CORRECT_MEDICAL_RISK_LIMIT_LEVEL", op.get().getMedicalRiskLimitLevel());
        assertEquals("", BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_DOWN), op.get().getPremium());
    }

    @Test
    public void incorrectFirstNameTest() {
        String firstName = "IncorrectFirstName";
        String lastName = "CorrectLastName";
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");

        Optional<AgreementPersonEntityDomain> op = agreementPersonEntityRepository.findByName(firstName, lastName, ic);

        assertFalse("", op.isPresent());
    }

    @Test
    public void incorrectLastNameTest() {
        String firstName = "CorrectFirstName";
        String lastName = "IncorrectLastName";
        UUID ic = UUID.fromString("12345678-1234-1234-1234-123456789101");

        Optional<AgreementPersonEntityDomain> op = agreementPersonEntityRepository.findByName(firstName, lastName, ic);

        assertFalse("", op.isPresent());
    }

    @Test
    public void incorrectPersonIcTest() {
        String firstName = "CorrectFirstName";
        String lastName = "CorrectLastName";
        UUID ic = UUID.fromString("22222222-1234-1234-1234-123456789101");

        Optional<AgreementPersonEntityDomain> op = agreementPersonEntityRepository.findByName(firstName, lastName, ic);

        assertFalse("", op.isPresent());
    }

    @Test
    public void twoPersons() {
        AgreementEntityDomain agreementEntityDomain =new AgreementEntityDomain();
        agreementEntityDomain.setId(1L);
        List<AgreementPersonEntityDomain> personDTODomains = agreementPersonEntityRepository.findByAgreement(agreementEntityDomain);
        assertEquals("", 2, personDTODomains.size());
    }
}
