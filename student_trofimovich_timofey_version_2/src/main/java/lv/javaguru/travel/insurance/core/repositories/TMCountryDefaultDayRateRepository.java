package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMCountryDefaultDayRate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TMCountryDefaultDayRateRepository extends JpaRepository<TMCountryDefaultDayRate, Long> {
    @Cacheable(value = "country_default_day_rate", key = "#countryIc", unless = "#result == null ")

    Optional<TMCountryDefaultDayRate> findByCountryIc(String countryIc);
}
