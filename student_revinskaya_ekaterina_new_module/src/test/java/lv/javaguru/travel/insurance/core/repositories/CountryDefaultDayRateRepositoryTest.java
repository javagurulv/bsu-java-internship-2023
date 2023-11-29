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
public class CountryDefaultDayRateRepositoryTest {
    @Autowired private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(countryDefaultDayRateRepository);
    }
    @Test
    public void shouldFindCountrySPAIN() {
        testValueByClassifierTitleAndIc("SPAIN");
    }
    @Test
    public void shouldFindCountryJAPAN() {
        testValueByClassifierTitleAndIc("JAPAN");
    }
    @Test
    public void shouldFindCountryLATVIA() {
        testValueByClassifierTitleAndIc("LATVIA");
    }

    @Test
    public void shouldNotFind_RiskType_FAKE() {
        Optional<CountryDefaultDayRate> valueOpt = countryDefaultDayRateRepository.findByCountryIc(
                "FAKE");
        assertTrue(valueOpt.isEmpty());
    }
    public void testValueByClassifierTitleAndIc(String countryIc){
        Optional<CountryDefaultDayRate> valueOpt = countryDefaultDayRateRepository.findByCountryIc(countryIc);
        assertTrue(valueOpt.isPresent());
        assertEquals(valueOpt.get().getCountryIc(), countryIc);
    }
}
