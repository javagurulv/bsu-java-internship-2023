package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.domain.CountrySafetyRatingCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CountrySafetyRatingCoefficientRepository extends JpaRepository<CountrySafetyRatingCoefficient, Long> {

    Optional<CountrySafetyRatingCoefficient> findByCountryIc(String countryIc);
}
