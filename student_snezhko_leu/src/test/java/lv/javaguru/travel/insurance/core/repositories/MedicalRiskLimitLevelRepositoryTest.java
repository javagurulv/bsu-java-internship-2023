package lv.javaguru.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        Double coefficient = 1.0;
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel15000Test() {
        String ic = "LEVEL_15000";
        Double coefficient = 1.2;
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel20000Test() {
        String ic = "LEVEL_20000";
        Double coefficient = 1.5;
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }

    @Test
    public void MedicalRiskLimitLevelRepositoryLevel50000Test() {
        String ic = "LEVEL_50000";
        Double coefficient = 2.0;
        assertEquals(coefficient, mrllRepository.findCoefficientByLimitLevelIc(ic).get().getCoefficient());
    }
}
