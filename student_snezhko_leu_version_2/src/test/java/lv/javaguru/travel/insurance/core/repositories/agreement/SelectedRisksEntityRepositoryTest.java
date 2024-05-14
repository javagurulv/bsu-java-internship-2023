package lv.javaguru.travel.insurance.core.repositories.agreement;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementRiskEntityDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SelectedRisksEntityRepositoryTest {
    @Autowired
    private AgreementRiskEntityRepository agreementRiskEntityRepository;

    @Test
    public void isExistingRepository() {
        assertNotNull("", agreementRiskEntityRepository);
    }

    @Test
    public void correctRiskIc() {
        String riskIc = "CORRECT_RISK_IC";
        AgreementEntityDomain agreementEntityDomain = new AgreementEntityDomain();
        agreementEntityDomain.setId(1L);
        Optional<AgreementRiskEntityDomain> domain = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, agreementEntityDomain);
        assertTrue("", domain.isPresent());
        assertEquals("", riskIc, domain.get().getRiskIc());
    }

    @Test
    public void incorrectRiskIc() {
        String riskIc = "WRONG_RISK_IC";
        AgreementEntityDomain agreementEntityDomain = new AgreementEntityDomain();
        agreementEntityDomain.setId(1L);
        Optional<AgreementRiskEntityDomain> domain = agreementRiskEntityRepository.findByIcAndAgreement(riskIc, agreementEntityDomain);
        assertFalse("", domain.isPresent());
    }
}
