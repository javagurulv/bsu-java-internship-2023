package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementPersonEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonDTODomain;
import lv.javaguru.travel.insurance.core.domain.agreement.PersonRiskEntityDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRiskEntityRepositoryTest {
    @Autowired
    private PersonRiskEntityRepository personRiskEntityRepository;

    @Test
    public void isRepositoryExists() {
        assertNotNull("", personRiskEntityRepository);
    }

    @Test
    public void correctIcAndPerson() {
        String ic = "CORRECT_RISK_IC";
        BigDecimal premium = BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP);
        AgreementPersonEntityDomain domain = new AgreementPersonEntityDomain();
        domain.setId(1L);
        Optional<PersonRiskEntityDomain> risk = personRiskEntityRepository.findByIcAndPerson(ic, domain);
        assertTrue("", risk.isPresent());
        assertEquals("", ic, risk.get().getRiskIc());
        assertEquals("", premium, risk.get().getPremium());
        assertEquals("", 1L, risk.get().getPerson().getId());
    }

    @Test
    public void incorrectIcTest() {
        String ic = "INCORRECT_RISK_IC";
        BigDecimal premium = BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP);
        AgreementPersonEntityDomain domain = new AgreementPersonEntityDomain();
        domain.setId(1L);
        Optional<PersonRiskEntityDomain> risk = personRiskEntityRepository.findByIcAndPerson(ic, domain);
        assertFalse("", risk.isPresent());
    }

    @Test
    public void incorrectPersonTest() {
        String ic = "CORRECT_RISK_IC";
        BigDecimal premium = BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP);
        AgreementPersonEntityDomain domain = new AgreementPersonEntityDomain();
        domain.setId(0L);
        Optional<PersonRiskEntityDomain> risk = personRiskEntityRepository.findByIcAndPerson(ic, domain);
        assertFalse("", risk.isPresent());
    }
}
