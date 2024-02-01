package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCCountrySafeRatingCoefficient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TCCountrySafeRatingCoefficientRepositoryTest {

    @Autowired
    private TCCountrySafeRatingCoefficientRepository repository;

    @Test
    void injectedRepositoryIsNotNull() {
        assertThat(repository).isNotNull();
    }
    @ParameterizedTest
    @ValueSource(strings = {"LATVIA", "SPAIN", "JAPAN"})
    void shouldFindCountryRate(String country) {
        checkIfRateForCountryExists(country);
    }

    @Test
    void shouldNotFindCountryRate() {
        Optional<TCCountrySafeRatingCoefficient> rate = getCountryRate("DUMMY");
        assertThat(rate.isEmpty());
    }

    private Optional<TCCountrySafeRatingCoefficient> getCountryRate(String countryIc) {
        return repository.findByCountryIc(countryIc);
    }

    private void checkIfRateForCountryExists(String countryIc) {
        Optional<TCCountrySafeRatingCoefficient> rate = getCountryRate(countryIc);
        assertThat(rate.isPresent());
        assertThat(rate.get().getCountryIc()).isEqualTo(countryIc);
    }
}
