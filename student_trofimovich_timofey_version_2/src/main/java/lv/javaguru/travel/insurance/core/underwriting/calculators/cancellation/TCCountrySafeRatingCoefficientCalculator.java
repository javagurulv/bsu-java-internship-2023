package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TCCountrySafeRatingCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCCountrySafeRatingCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TCCountrySafeRatingCoefficientCalculator {

    @Autowired
    private TCCountrySafeRatingCoefficientRepository repository;

    BigDecimal getCountrySafeRatingCoefficient(AgreementDTO agreement) {
        Optional<TCCountrySafeRatingCoefficient> countryRate = repository.findByCountryIc(agreement.getCountry());
        return countryRate.map(TCCountrySafeRatingCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Country safe rating not found for country: " + agreement.getCountry()));
    }
}
