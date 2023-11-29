package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCCountrySafetyRatingCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TCCountrySafetyRatingCoefficientCalculator {

    @Autowired private TCCountrySafetyRatingCoefficientRepository countrySafetyRatingCoefficientRepository;

    BigDecimal calculate(AgreementDTO agreement) {
        return countrySafetyRatingCoefficientRepository.findByCountryIc(agreement.getCountry())
                .map(TCCountrySafetyRatingCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Country safety rating coefficient not found by country id = " + agreement.getCountry()));
    }

}
