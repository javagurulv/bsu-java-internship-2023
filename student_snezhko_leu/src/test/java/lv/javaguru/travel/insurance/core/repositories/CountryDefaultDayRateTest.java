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
public class CountryDefaultDayRateTest {
    @Autowired
    private CountryDefaultDayRateRepository cddrRepository;

    @Test
    public void notNullRepository() {
        assertNotNull(cddrRepository);
    }

    @Test
    public void returnCorrectValueLatviaTest() {
        String countryName = "LATVIA";
        Optional<CountryDefaultDayRate> cddr = cddrRepository.findByCountryIc(countryName);
        assertEquals(1.00d, cddr.get().getCountryDefaultDayRate());
    }

    @Test
    public void returnCorrectValueSpainTest() {
        String countryName = "SPAIN";
        Double expectedValue = 2.50d;
        Optional<CountryDefaultDayRate> cddr = cddrRepository.findByCountryIc(countryName);
        assertEquals(expectedValue, cddr.get().getCountryDefaultDayRate());
    }

    @Test
    public void returnCorrectValueJapanTest() {
        String countryName = "JAPAN";
        Double expectedValue = 3.50d;
        Optional<CountryDefaultDayRate> cddr = cddrRepository.findByCountryIc(countryName);
        assertEquals(expectedValue, cddr.get().getCountryDefaultDayRate());
    }

    @Test
    public void processIncorrectValueTest() {
        String countryName = "NOT_EXISTING_COUNTRY";
        Optional<CountryDefaultDayRate> cddr = cddrRepository.findByCountryIc(countryName);
        assertTrue(cddr.isEmpty());
    }

}
