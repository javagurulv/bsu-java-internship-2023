package lv.javaguru.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AgeCoefficientRepositoryTest {
    @Autowired
    AgeCoefficientRepository acRepository;

    @Test
    public void ageCoefficientRepositoryNotNullTest() {
        assertNotNull(acRepository);
    }

    @Test
    public void ageCoefficientFromZeroToFiveTest() {
        assertEquals(BigDecimal.valueOf(1.1).setScale(2, RoundingMode.HALF_UP), acRepository.findByAgeFromAndAgeTo(3).get().getCoefficient());
        //assertEquals(acRepository.findByAgeFromAndAgeTo(0, 5).get().getCoefficient(), 1.1);
    }

    @Test
    public void ageCoefficientFromSixToTenTest() {
        //assertEquals(acRepository.findByAgeFromAndAgeTo(6, 10).get().getCoefficient(), 0.7);
        assertEquals(BigDecimal.valueOf(0.7).setScale(2, RoundingMode.HALF_UP), acRepository.findByAgeFromAndAgeTo(8).get().getCoefficient());
    }

    @Test
    public void ageCoefficientFromElevenToSeventeenTest() {
        //assertEquals(acRepository.findByAgeFromAndAgeTo(11, 17).get().getCoefficient(), 1.0);
        assertEquals(BigDecimal.valueOf(1.0).setScale(2, RoundingMode.HALF_UP), acRepository.findByAgeFromAndAgeTo(15).get().getCoefficient());
    }

    @Test
    public void ageCoefficientFromEighteenToFortyTest() {
        //assertEquals(acRepository.findByAgeFromAndAgeTo(18, 40).get().getCoefficient(), 1.1);
        assertEquals(BigDecimal.valueOf(1.1).setScale(2, RoundingMode.HALF_UP), acRepository.findByAgeFromAndAgeTo(30).get().getCoefficient());
    }

    @Test
    public void ageCoefficientFromFortyOneToSixtyFiveTest() {
        //assertEquals(acRepository.findByAgeFromAndAgeTo(41, 65).get().getCoefficient(), 1.2);
        assertEquals(BigDecimal.valueOf(1.2).setScale(2, RoundingMode.HALF_UP), acRepository.findByAgeFromAndAgeTo(52).get().getCoefficient());
    }

    @Test
    public void ageCoefficientFromSixtySixToOneHundredFiftyTest() {
        //assertEquals(acRepository.findByAgeFromAndAgeTo(66, 150).get().getCoefficient(), 1.5);
        assertEquals(BigDecimal.valueOf(1.5).setScale(2, RoundingMode.HALF_UP), acRepository.findByAgeFromAndAgeTo(100).get().getCoefficient());
    }
}
