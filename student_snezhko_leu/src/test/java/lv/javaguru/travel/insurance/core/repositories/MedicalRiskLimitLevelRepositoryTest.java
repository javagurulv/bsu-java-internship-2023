package lv.javaguru.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MedicalRiskLimitLevelRepositoryTest {
    @Autowired
    private MedicalRiskLimitLevelRepository mrllRepository;

    @Test
    public void MedicalRiskLimitLevelRepositoryNotNullTest() {
        assertNotNull(mrllRepository);
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel10000Test() {
        String ic = "LEVEL_10000";
        BigDecimal coefficient = BigDecimal.valueOf(1.0).setScale(2, RoundingMode.HALF_UP);
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel15000Test() {
        String ic = "LEVEL_15000";
        BigDecimal coefficient = BigDecimal.valueOf(1.2).setScale(2, RoundingMode.HALF_UP);
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel20000Test() {
        String ic = "LEVEL_20000";
        BigDecimal coefficient = BigDecimal.valueOf(1.5).setScale(2, RoundingMode.HALF_UP);
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel50000Test() {
        String ic = "LEVEL_50000";
        BigDecimal coefficient = BigDecimal.valueOf(2.0).setScale(2, RoundingMode.HALF_UP);
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }
}
