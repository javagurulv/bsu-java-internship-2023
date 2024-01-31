package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCCountrySafeRatingCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCountrySafeRatingCoefficientRepository extends JpaRepository<TCCountrySafeRatingCoefficient, Long> {
    Optional<TCCountrySafeRatingCoefficient> findByCountryIc(String countryIc);

}
