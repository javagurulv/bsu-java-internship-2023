package lv.javaguru.travel.insurance.core.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;

import java.util.Optional;

public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate, Long> {

    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);
}
