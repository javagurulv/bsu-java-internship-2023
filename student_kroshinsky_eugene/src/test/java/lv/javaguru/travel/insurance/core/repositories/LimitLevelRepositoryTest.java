package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.domain.LimitLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class LimitLevelRepositoryTest {
    @Autowired
    private LimitLevelRepository limitLevelRepository;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(limitLevelRepository);
    }
    @Test
    void findLimit10000() {
        shouldFindByIc("LEVEL_10000");
    }
    @Test
    void findLimit15000() {
        shouldFindByIc("LEVEL_15000");
    }
    @Test
    void findLimit20000() {
        shouldFindByIc("LEVEL_20000");
    }
    @Test
    void findLimit50000() {
        shouldFindByIc("LEVEL_50000");
    }
    @Test
    public void findFake() {
        Optional<LimitLevel> valueOpt = limitLevelRepository.findByIc("FAKE");
        assertTrue(valueOpt.isEmpty());
    }
    private void shouldFindByIc(String ic) {
        Optional<LimitLevel> valueOptional = limitLevelRepository.findByIc(ic);
        assertTrue(valueOptional.isPresent());
        assertEquals(ic, valueOptional.get().getIc());
    }
}