package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryDefaultDayRateRepositoryTest {
    @Autowired
    private CountryDefaultDayRateRepository repository;

    @Test
    void injectedRepositoryIsNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    void shouldFindCountryRateForLatvia() {
        checkIfRateForCountryExists("LATVIA");
    }

    @Test
    void shouldFindCountryRateForSpain() {
        checkIfRateForCountryExists("SPAIN");
    }

    @Test
    void shouldFindCountryRateForJapan() {
        checkIfRateForCountryExists("JAPAN");
    }

    @Test
    void shouldNotFindCountryRate() {
        Optional<CountryDefaultDayRate> rate = getCountryRate("DUMMY");
        assertThat(rate.isEmpty());
    }

    private Optional<CountryDefaultDayRate> getCountryRate(String countryIc) {
        return repository.findByCountryIc(countryIc);
    }

    private void checkIfRateForCountryExists(String countryIc) {
        Optional<CountryDefaultDayRate> rate = getCountryRate(countryIc);
        assertThat(rate.isPresent());
        assertThat(rate.get().getCountryIc()).isEqualTo(countryIc);
    }
}
