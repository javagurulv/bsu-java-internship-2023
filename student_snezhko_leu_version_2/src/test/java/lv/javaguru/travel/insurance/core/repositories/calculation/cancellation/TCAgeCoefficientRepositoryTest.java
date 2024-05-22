package lv.javaguru.travel.insurance.core.repositories.calculation.cancellation;

import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TCAgeCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TCAgeCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TCAgeCoefficientRepositoryTest {
    @Autowired
    private TCAgeCoefficientRepository ageCoefficientRepository;

    @Test
    public void isRepoNotNull() {
        assertNotNull("", ageCoefficientRepository);
    }

    @Test
    public void age0Coefficient() {
        Optional<TCAgeCoefficientDomain> optional = ageCoefficientRepository.findCoefficient(0);
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(5L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void age10Coefficient() {
        Optional<TCAgeCoefficientDomain> optional = ageCoefficientRepository.findCoefficient(10);
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(10L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void age18Coefficient() {
        Optional<TCAgeCoefficientDomain> optional = ageCoefficientRepository.findCoefficient(18);
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(20L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void age40Coefficient() {
        Optional<TCAgeCoefficientDomain> optional = ageCoefficientRepository.findCoefficient(40);
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(30L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void age66Coefficient() {
        Optional<TCAgeCoefficientDomain> optional = ageCoefficientRepository.findCoefficient(66);
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(50L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }
}
