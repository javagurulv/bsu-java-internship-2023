package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCountrySafetyRatingCoefficientRepository
        extends JpaRepository<TCCountrySafetyRatingCoefficient, Long> {

    Optional<TCCountrySafetyRatingCoefficient> findByCountryIc(String countryIc);

}
