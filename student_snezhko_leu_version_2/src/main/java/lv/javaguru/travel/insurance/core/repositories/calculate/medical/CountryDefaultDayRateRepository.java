package lv.javaguru.travel.insurance.core.repositories.calculate.medical;

import lv.javaguru.travel.insurance.core.domain.calculate.medical.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository
        extends JpaRepository<CountryDefaultDayRate, Long> {

    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);

}
