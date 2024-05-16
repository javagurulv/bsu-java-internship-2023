package lv.javaguru.travel.insurance.core.repositories.calculation.cancellation;

import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TravelCostCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TravelCostCoefficientRepository;
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
public class TravelCostCoefficientRepositoryTest {
    @Autowired
    private TravelCostCoefficientRepository travelCostCoefficientRepository;

    @Test
    public void repositoryIsNotNull() {
        assertNotNull("", travelCostCoefficientRepository);
    }

    @Test
    public void cost0Test() {
        Optional<TravelCostCoefficientDomain> optional = travelCostCoefficientRepository.findCoefficientByCost(BigDecimal.ZERO);
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(10L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void cost5000Test() {
        Optional<TravelCostCoefficientDomain> optional = travelCostCoefficientRepository.findCoefficientByCost(BigDecimal.valueOf(6000L));
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(30L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void cost10000Test() {
        Optional<TravelCostCoefficientDomain> optional = travelCostCoefficientRepository.findCoefficientByCost(BigDecimal.valueOf(11000L));
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(100L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }

    @Test
    public void cost20000Test() {
        Optional<TravelCostCoefficientDomain> optional = travelCostCoefficientRepository.findCoefficientByCost(BigDecimal.valueOf(21000L));
        assertTrue("", optional.isPresent());
        assertEquals("", BigDecimal.valueOf(500L).setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }
}
