package lv.javaguru.travel.insurance.core.repositories.get;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@DataJpaTest
public class GetAgreementRepositoryTest {
    @Autowired
    private GetAgreementRepository getAgreementRepository;

    @Test
    public void repositoryNotNull() {
        assertNotNull("", getAgreementRepository);
    }

    /*
    ("11111111-1111-1111-1111-111111111111",
'2050-02-02',
'2050-02-05',
'LATVIA',
2);
     */
    @Test
    public void correctUUID() {
        UUID uuid = UUID.fromString("11111111-1111-1111-1111-111111111111");
        Date dateFrom = Date.valueOf("2050-02-02");
        Date dateTo = Date.valueOf("2050-02-05");
        String country = "LATVIA";
        BigDecimal premium = BigDecimal.ONE.add(BigDecimal.ONE).setScale(2);
        Optional<AgreementEntityDomain> optional = getAgreementRepository.findByUuid(uuid);
        assertTrue("", optional.isPresent());
        assertEquals("", dateFrom, optional.get().getDateFrom());
        assertEquals("", dateTo, optional.get().getDateTo());
        assertEquals("", country, optional.get().getCountry());
        assertEquals("", premium, optional.get().getPremium());
    }

    @Test
    public void incorrectUUID() {
        UUID uuid = UUID.fromString("22222222-2222-2222-2222-222222222222");
        Optional<AgreementEntityDomain> optional = getAgreementRepository.findByUuid(uuid);
        assertTrue("", optional.isEmpty());
    }
}
