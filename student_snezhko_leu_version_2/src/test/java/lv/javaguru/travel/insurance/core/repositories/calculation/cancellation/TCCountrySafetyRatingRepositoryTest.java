package lv.javaguru.travel.insurance.core.repositories.calculation.cancellation;

import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TCCountrySafetyRatingCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TCCountrySafetyRatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TCCountrySafetyRatingRepositoryTest {
    @Autowired
    private TCCountrySafetyRatingRepository repository;

    @Test
    public void repositoryIsNotNUll() {
        assertNotNull("",repository);
    }

    @Test
    public void safetyRatingLatviaTest() {
        String country = "LATVIA";
        Integer expectedCoefficient = 5;
        Optional<TCCountrySafetyRatingCoefficientDomain> optional = repository.findByCountry(country);
        assertTrue("", optional.isPresent());
        assertEquals("", expectedCoefficient, optional.get().getRating());
    }

    @Test
    public void safetyRatingSpainTest() {
        String country = "SPAIN";
        Integer expectedCoefficient = 8;
        Optional<TCCountrySafetyRatingCoefficientDomain> optional = repository.findByCountry(country);
        assertTrue("", optional.isPresent());
        assertEquals("", expectedCoefficient, optional.get().getRating());
    }

    @Test
    public void safetyRatingJapanTest() {
        String country = "JAPAN";
        Integer expectedCoefficient = 9;
        Optional<TCCountrySafetyRatingCoefficientDomain> optional = repository.findByCountry(country);
        assertTrue("", optional.isPresent());
        assertEquals("", expectedCoefficient, optional.get().getRating());
    }

    @Test
    public void safetyRatingNotExistingCountryTest() {
        String country = "NOT_EXISTING_COUNTRY";
        Optional<TCCountrySafetyRatingCoefficientDomain> optional = repository.findByCountry(country);
        assertTrue("", optional.isEmpty());
    }
}
