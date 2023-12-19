package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MedicalRiskLimitLevelRepositoryTest {
    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Test
    public void shouldFind_LEVEL_10000() {
        testValueByRiskLimitLevel("LEVEL_10000");
    }

    @Test
    public void shouldFind_LEVEL_15000() {
        testValueByRiskLimitLevel("LEVEL_15000");
    }

    @Test
    public void shouldFind_LEVEL_20000() {
        testValueByRiskLimitLevel("LEVEL_20000");
    }

    @Test
    public void shouldFind_LEVEL_50000() {
        testValueByRiskLimitLevel("LEVEL_50000");
    }

    @Test
    public void shouldNotFindRiskLimitLevel() {
        Optional<MedicalRiskLimitLevel> valueOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("FAKE");
        assertTrue(valueOpt.isEmpty());
    }

    public void testValueByRiskLimitLevel(String ic) {
        Optional<MedicalRiskLimitLevel> valueOpt = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(ic);
        assertTrue(valueOpt.isPresent());
        assertEquals(valueOpt.get().getMedicalRiskLimitLevelIc(), ic);
    }
}
