package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TMCountryDefaultDayRateRepositoryTest {

    @Autowired
    private TMCountryDefaultDayRateRepository TMCountryDefaultDayRateRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(TMCountryDefaultDayRateRepository);
    }

    @Test
    public void shouldFindForLatvia() {
        searchCountryDefaultDayRate("LATVIA", new BigDecimal("1.00"));
    }

    @Test
    public void shouldFindForSpain() {
        searchCountryDefaultDayRate("SPAIN", new BigDecimal("2.50"));
    }

    @Test
    public void shouldFindForJapan() {
        searchCountryDefaultDayRate("JAPAN", new BigDecimal("3.50"));
    }

    @Test
    public void shouldNotFindForUnknownCountry() {
        Optional<TMCountryDefaultDayRate> valueOpt = TMCountryDefaultDayRateRepository.findByCountryIc("FAKE_COUNTRY");
        assertTrue(valueOpt.isEmpty());
    }

    private void searchCountryDefaultDayRate(String countryIc, BigDecimal dayRate) {
        Optional<TMCountryDefaultDayRate> valueOpt = TMCountryDefaultDayRateRepository.findByCountryIc(countryIc);
        assertTrue(valueOpt.isPresent());
        assertEquals(valueOpt.get().getCountryIc(), countryIc);
        assertEquals(valueOpt.get().getDefaultDayRate(), dayRate);

        assertEquals(dayRate.stripTrailingZeros(),
                valueOpt.get().getDefaultDayRate().stripTrailingZeros());
    }

}