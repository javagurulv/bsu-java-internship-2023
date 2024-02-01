package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TCCountrySafeRatingCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCountrySafeRatingCoefficientRepository extends JpaRepository<TCCountrySafeRatingCoefficient, Long> {
    @Cacheable(value = "country_safe_rating_coefficient", key = "#countryIc", unless = "#result == null ")
    Optional<TCCountrySafeRatingCoefficient> findByCountryIc(String countryIc);

}
