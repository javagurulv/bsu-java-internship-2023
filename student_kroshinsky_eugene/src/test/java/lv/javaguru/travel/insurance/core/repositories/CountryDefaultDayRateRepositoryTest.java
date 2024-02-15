package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryDefaultDayRateRepositoryTest {
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(countryDefaultDayRateRepository);
    }
    @Test
    void findLatvia() {
        shouldFindByIc("LATVIA");
    }
    @Test
    void findSpain() {
        shouldFindByIc("SPAIN");
    }
    @Test
    void findJapan() {
        shouldFindByIc("JAPAN");
    }
    @Test
    public void findFake() {
        Optional<CountryDefaultDayRate> valueOpt = countryDefaultDayRateRepository.findByIc("FAKE");
        assertTrue(valueOpt.isEmpty());
    }
    private void shouldFindByIc(String ic) {
        Optional<CountryDefaultDayRate> valueOptional = countryDefaultDayRateRepository.findByIc(ic);
        assertTrue(valueOptional.isPresent());
        assertEquals(ic, valueOptional.get().getIc());
    }
}