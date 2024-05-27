package lv.javaguru.travel.insurance.core.repositories.calculate.cancellation;

import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TCCountrySafetyRatingCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCCountrySafetyRatingRepository extends JpaRepository<TCCountrySafetyRatingCoefficientDomain, Long> {
    public Optional<TCCountrySafetyRatingCoefficientDomain> findByCountry(String country);
}
