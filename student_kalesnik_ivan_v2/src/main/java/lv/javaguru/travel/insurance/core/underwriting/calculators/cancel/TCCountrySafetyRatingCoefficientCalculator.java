package lv.javaguru.travel.insurance.core.underwriting.calculators.cancel;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.domain.TCCountrySafetyRatingCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCCountrySafetyRatingCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TCCountrySafetyRatingCoefficientCalculator {

    @Autowired private TCCountrySafetyRatingCoefficientRepository countrySafetyRatingCoefficientRepository;

    BigDecimal calculate(AgreementDto agreement) {
        return countrySafetyRatingCoefficientRepository.findByCountryIc(agreement.getCountry())
                .map(TCCountrySafetyRatingCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Country safety rating coefficient not found by country id = " + agreement.getCountry()));
    }

}
